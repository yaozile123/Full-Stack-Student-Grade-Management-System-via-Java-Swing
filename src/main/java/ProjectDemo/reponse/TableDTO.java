package ProjectDemo.reponse;

import java.util.Vector;

public class TableDTO {
    private Vector<Vector<Object>> data; // data for our main table
    private int totalCount; // how many rows of data

    public Vector<Vector<Object>> getData() {
        return data;
    }

    public void setData(Vector<Vector<Object>> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
