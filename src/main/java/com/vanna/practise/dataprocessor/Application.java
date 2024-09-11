package com.vanna.practise.dataprocessor;

import com.vanna.practise.dataprocessor.data.SaleRecord;
import com.vanna.practise.dataprocessor.operations.DataProcessor;
import com.vanna.practise.dataprocessor.operations.SalesDataProcessor;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        DataProcessor salesDataProcessor = new SalesDataProcessor();
        List<SaleRecord> data = salesDataProcessor.processSalesFromSourceFile("D:\\Projects\\Labs\\1000000 Sales Records.csv");
        salesDataProcessor.writeProcessedSaleRecords(data, "D:\\Projects\\Labs\\sales_records_out.csv");
    }
}
