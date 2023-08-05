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
package uk.org.smithfamily.mslogger.chart;

import uk.org.smithfamily.mslogger.chart.chart.CombinedXYChart;
import uk.org.smithfamily.mslogger.chart.chart.LineChart;
import uk.org.smithfamily.mslogger.chart.chart.ScatterChart;
import uk.org.smithfamily.mslogger.chart.chart.XYChart;
import uk.org.smithfamily.mslogger.chart.model.XYMultipleSeriesDataset;
import uk.org.smithfamily.mslogger.chart.renderer.XYMultipleSeriesRenderer;
import android.content.Context;
import android.content.Intent;

/**
 * Utility methods for creating chart views or intents.
 */
public class ChartFactory {
  /** The key for the chart data. */
  public static final String CHART = "chart";

  /** The key for the chart graphical activity title. */
  public static final String TITLE = "title";

  private ChartFactory() {
    // empty for now
  }

  /**
   * Creates a line chart view.
   * 
   * @param context the context
   * @param dataset the multiple series dataset (cannot be null)
   * @param renderer the multiple series renderer (cannot be null)
   * @return a line chart graphical view
   * @throws IllegalArgumentException if dataset is null or renderer is null or
   *           if the dataset and the renderer don't include the same number of
   *           series
   */
  public static final GraphicalView getLineChartView(Context context,
      XYMultipleSeriesDataset dataset, XYMultipleSeriesRenderer renderer) {
    checkParameters(dataset, renderer);
    XYChart chart = new LineChart(dataset, renderer);
    return new GraphicalView(context, chart);
  }

  /**
   * Creates a scatter chart view.
   * 
   * @param context the context
   * @param dataset the multiple series dataset (cannot be null)
   * @param renderer the multiple series renderer (cannot be null)
   * @return a scatter chart graphical view
   * @throws IllegalArgumentException if dataset is null or renderer is null or
   *           if the dataset and the renderer don't include the same number of
   *           series
   */
  public static final GraphicalView getScatterChartView(Context context,
      XYMultipleSeriesDataset dataset, XYMultipleSeriesRenderer renderer) {
    checkParameters(dataset, renderer);
    XYChart chart = new ScatterChart(dataset, renderer);
    return new GraphicalView(context, chart);
  }
  
  /**
   * Creates a combined XY chart view.
   * 
   * @param context the context
   * @param dataset the multiple series dataset (cannot be null)
   * @param renderer the multiple series renderer (cannot be null)
   * @param types the chart types (cannot be null)
   * @return a combined XY chart graphical view
   * @throws IllegalArgumentException if dataset is null or renderer is null or
   *           if a dataset number of items is different than the number of
   *           series renderers or number of chart types
   */
  public static final GraphicalView getCombinedXYChartView(Context context,
      XYMultipleSeriesDataset dataset, XYMultipleSeriesRenderer renderer, String[] types) {
    if (dataset == null || renderer == null || types == null
        || dataset.getSeriesCount() != types.length) {
      throw new IllegalArgumentException(
          "Dataset, renderer and types should be not null and the datasets series count should be equal to the types length");
    }
    checkParameters(dataset, renderer);
    CombinedXYChart chart = new CombinedXYChart(dataset, renderer, types);
    return new GraphicalView(context, chart);
  }

  /**
   * 
   * Creates a line chart intent that can be used to start the graphical view
   * activity.
   * 
   * @param context the context
   * @param dataset the multiple series dataset (cannot be null)
   * @param renderer the multiple series renderer (cannot be null)
   * @return a line chart intent
   * @throws IllegalArgumentException if dataset is null or renderer is null or
   *           if the dataset and the renderer don't include the same number of
   *           series
   */
  public static final Intent getLineChartIntent(Context context, XYMultipleSeriesDataset dataset,
      XYMultipleSeriesRenderer renderer) {
    return getLineChartIntent(context, dataset, renderer, "");
  }

  /**
   * Creates a scatter chart intent that can be used to start the graphical view
   * activity.
   * 
   * @param context the context
   * @param dataset the multiple series dataset (cannot be null)
   * @param renderer the multiple series renderer (cannot be null)
   * @return a scatter chart intent
   * @throws IllegalArgumentException if dataset is null or renderer is null or
   *           if the dataset and the renderer don't include the same number of
   *           series
   */
  public static final Intent getScatterChartIntent(Context context,
      XYMultipleSeriesDataset dataset, XYMultipleSeriesRenderer renderer) {
    return getScatterChartIntent(context, dataset, renderer, "");
  }

  /**
   * Creates a line chart intent that can be used to start the graphical view
   * activity.
   * 
   * @param context the context
   * @param dataset the multiple series dataset (cannot be null)
   * @param renderer the multiple series renderer (cannot be null)
   * @param activityTitle the graphical chart activity title. If this is null,
   *          then the title bar will be hidden. If a blank title is passed in,
   *          then the title bar will be the default. Pass in any other string
   *          to set a custom title.
   * @return a line chart intent
   * @throws IllegalArgumentException if dataset is null or renderer is null or
   *           if the dataset and the renderer don't include the same number of
   *           series
   */
  public static final Intent getLineChartIntent(Context context, XYMultipleSeriesDataset dataset,
      XYMultipleSeriesRenderer renderer, String activityTitle) {
    checkParameters(dataset, renderer);
    Intent intent = new Intent(context, GraphicalActivity.class);
    XYChart chart = new LineChart(dataset, renderer);
    intent.putExtra(CHART, chart);
    intent.putExtra(TITLE, activityTitle);
    return intent;
  }

  /**
   * Creates a scatter chart intent that can be used to start the graphical view
   * activity.
   * 
   * @param context the context
   * @param dataset the multiple series dataset (cannot be null)
   * @param renderer the multiple series renderer (cannot be null)
   * @param activityTitle the graphical chart activity title
   * @return a scatter chart intent
   * @throws IllegalArgumentException if dataset is null or renderer is null or
   *           if the dataset and the renderer don't include the same number of
   *           series
   */
  public static final Intent getScatterChartIntent(Context context,
      XYMultipleSeriesDataset dataset, XYMultipleSeriesRenderer renderer, String activityTitle) {
    checkParameters(dataset, renderer);
    Intent intent = new Intent(context, GraphicalActivity.class);
    XYChart chart = new ScatterChart(dataset, renderer);
    intent.putExtra(CHART, chart);
    intent.putExtra(TITLE, activityTitle);
    return intent;
  }

  /**
   * Creates a combined XY chart intent that can be used to start the graphical
   * view activity.
   * 
   * @param context the context
   * @param dataset the multiple series dataset (cannot be null)
   * @param renderer the multiple series renderer (cannot be null)
   * @param types the chart types (cannot be null)
   * @param activityTitle the graphical chart activity title
   * @return a combined XY chart intent
   * @throws IllegalArgumentException if dataset is null or renderer is null or
   *           if a dataset number of items is different than the number of
   *           series renderers or number of chart types
   */
  public static final Intent getCombinedXYChartIntent(Context context,
      XYMultipleSeriesDataset dataset, XYMultipleSeriesRenderer renderer, String[] types,
      String activityTitle) {
    if (dataset == null || renderer == null || types == null
        || dataset.getSeriesCount() != types.length) {
      throw new IllegalArgumentException(
          "Datasets, renderers and types should be not null and the datasets series count should be equal to the types length");
    }
    checkParameters(dataset, renderer);
    Intent intent = new Intent(context, GraphicalActivity.class);
    CombinedXYChart chart = new CombinedXYChart(dataset, renderer, types);
    intent.putExtra(CHART, chart);
    intent.putExtra(TITLE, activityTitle);
    return intent;
  }

  /**
   * Checks the validity of the dataset and renderer parameters.
   * 
   * @param dataset the multiple series dataset (cannot be null)
   * @param renderer the multiple series renderer (cannot be null)
   * @throws IllegalArgumentException if dataset is null or renderer is null or
   *           if the dataset and the renderer don't include the same number of
   *           series
   */
  private static void checkParameters(XYMultipleSeriesDataset dataset,
      XYMultipleSeriesRenderer renderer) {
    if (dataset == null || renderer == null
        || dataset.getSeriesCount() != renderer.getSeriesRendererCount()) {
      throw new IllegalArgumentException(
          "Dataset and renderer should be not null and should have the same number of series");
    }
  }
}
