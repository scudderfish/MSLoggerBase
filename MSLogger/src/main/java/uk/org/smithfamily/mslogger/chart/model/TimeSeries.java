/**
 * Copyright (C) 2009 - 2012 SC 4ViewSoft SRL
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *      http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.org.smithfamily.mslogger.chart.model;

import java.util.Date;

/**
 * A series for the date / time charts.
 */
public class TimeSeries extends XYSeries {

  /**
     * 
     */
    private static final long serialVersionUID = 5044670968544473398L;

/**
   * Builds a new date / time series.
   * 
   * @param title the series title
   */
  public TimeSeries(String title) {
    super(title);
  }

  /**
   * Adds a new value to the series.
   * 
   * @param x the date / time value for the X axis
   * @param y the value for the Y axis
   */
  public synchronized void add(Date x, double y) {
    super.add(x.getTime(), y);
  }
}
