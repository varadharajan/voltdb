/* This file is part of VoltDB.
 * Copyright (C) 2008-2012 VoltDB Inc.
 *
 * VoltDB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VoltDB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VoltDB.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.voltdb.iv2;

import java.util.concurrent.TimeUnit;

import org.voltdb.SiteProcedureConnection;
import org.voltdb.VoltDB;

/**
 * Runs the tick operation against the EE
 */
public class TickProducer extends SiteTasker implements Runnable
{
    private final SiteTaskerQueue m_taskQueue;

    public TickProducer(SiteTaskerQueue taskQueue)
    {
        m_taskQueue = taskQueue;
    }

    // start schedules a 1 second tick.
    public void start()
    {
        VoltDB.instance().scheduleWork(
                this,
                1,
                1,
                TimeUnit.SECONDS);
    }

    // Runnable.run() schedules execution
    @Override
    public void run()
    {
        m_taskQueue.offer(this);
    }

    @Override
    public void run(final SiteProcedureConnection siteConnection)
    {
        siteConnection.tick();
    }

    @Override
    public void runForRejoin(final SiteProcedureConnection siteConnection)
    {
        siteConnection.tick();
    }
}
