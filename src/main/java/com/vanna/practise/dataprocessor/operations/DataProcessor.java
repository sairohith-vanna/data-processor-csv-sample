package com.vanna.practise.dataprocessor.operations;

import com.vanna.practise.dataprocessor.data.SaleRecord;

import java.util.List;

public interface DataProcessor {

    List<SaleRecord> processSalesFromSourceFile(String csvPath);
    void writeProcessedSaleRecords(List<SaleRecord> saleRecords, String outputFilePath);
}
