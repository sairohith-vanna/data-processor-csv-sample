import com.vanna.practise.dataprocessor.data.SaleRecord;
import com.vanna.practise.dataprocessor.operations.DataProcessor;
import com.vanna.practise.dataprocessor.operations.SalesDataProcessor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SalesDataProcessorTests {

    @Test
    public void whenCalledDataProcessor_shouldGetRecords() {
        DataProcessor dataProcessor = new SalesDataProcessor();
        List<SaleRecord> data = dataProcessor.processSalesFromSourceFile("D:\\Projects\\Labs\\1000000 Sales Records.csv");
        assertFalse(data.isEmpty());
    }
}
