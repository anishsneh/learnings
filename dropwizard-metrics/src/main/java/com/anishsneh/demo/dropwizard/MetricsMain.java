package com.anishsneh.demo.dropwizard;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;

/**
 * The Class MetricsMain.
 * @author Anish Sneh
 */
public class MetricsMain {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger("com.anishsneh.demo.dropwizard");

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		
		final MetricsMain metricsMain = new MetricsMain();
		final MetricRegistry metrics = new MetricRegistry();
		
		metricsMain.initReport(metrics);
		
		Meter requests = metrics.meter("requests");
		requests.mark();
		wait600Seconds();
	}

	/**
	 * Inits the report.
	 *
	 * @param metrics the metrics
	 */
	public void initReport(final MetricRegistry metrics) {

		//Console reporter
		ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
		consoleReporter.start(1, TimeUnit.SECONDS);

		//CSV reporter
		CsvReporter csvReporter = CsvReporter.forRegistry(metrics).formatFor(Locale.US).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build(new File("/tmp/"));
		csvReporter.start(1, TimeUnit.SECONDS);

		//SLF4J reporter
		Slf4jReporter slf4jReporter = Slf4jReporter.forRegistry(metrics).outputTo(logger).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
		slf4jReporter.start(1, TimeUnit.SECONDS);

		//JMX reporter
		JmxReporter jmxReporter = JmxReporter.forRegistry(metrics).build();
		jmxReporter.start();
	}

	/**
	 * Wait600 seconds.
	 */
	static void wait600Seconds() {
		try {
			Thread.sleep(600 * 1000);
		} catch (InterruptedException e) {
		}
	}
}
