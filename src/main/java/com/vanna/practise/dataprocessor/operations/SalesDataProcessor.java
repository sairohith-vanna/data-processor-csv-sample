package com.vanna.practise.dataprocessor.operations;

import com.vanna.practise.dataprocessor.data.SaleRecord;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalesDataProcessor implements DataProcessor {
    private static final Logger LOGGER = Logger.getLogger(SalesDataProcessor.class.getName());

    public List<SaleRecord> processSalesFromSourceFile(String csvPath) {
        Path csvFilePath = Path.of(csvPath);
        try (Stream<String> records = Files.lines(csvFilePath)) {
            return records
                    .skip(1) // Skipping the first row of headers
                    .limit(Long.MAX_VALUE)
                    .map(x -> {
                        String[] saleRecordColumns = x.split(",");
                        try {
                            return new SaleRecord(
                                    Long.parseLong(saleRecordColumns[6]),
                                    getParsedDate(saleRecordColumns[5]),
                                    new BigDecimal(saleRecordColumns[11])
                            );
                        } catch (NumberFormatException ex) {
                            return new SaleRecord(
                                    Long.parseLong(saleRecordColumns[6]),
                                    getParsedDate(saleRecordColumns[5]),
                                    BigDecimal.valueOf(0.0)
                            );
                        }
                    })
                    .filter(x -> x.getOrderDate().getYear() >= 2012)
                    .sorted(Comparator.comparing(SaleRecord::getOrderDate))
                    .collect(Collectors.toList());
        } catch (IOException | NumberFormatException ex) {
            LOGGER.log(
                    Level.SEVERE,
                    "There was an error processing the file: " + ex.getMessage(),
                    ex
            );
        }
        return Collections.emptyList();
    }

    public void writeProcessedSaleRecords(List<SaleRecord> saleRecords, String outputFilePath) {
        Path outFilePath = Path.of(outputFilePath);
        String headerText = "Order ID,Order Date,Revenue";
        String outputSaleData = saleRecords.parallelStream().map(x -> String.join(
                ",",
                String.valueOf(x.getOrderId()),
                x.getOrderDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                x.getRevenue().toPlainString())
        ).collect(Collectors.joining("\n"));
        try {
            Files.write(outFilePath, (headerText + "\n" + outputSaleData).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDate getParsedDate(String sourceDateText) {
        try {
            return LocalDate.parse(sourceDateText, DateTimeFormatter.ofPattern("M/d/yyyy"));
        } catch (DateTimeParseException exception) {
            return LocalDate.parse(sourceDateText, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        } catch (Exception ex) {
            return LocalDate.now();
        }
    }
}
