package improve.sort.impl;

import com.alibaba.fastjson.JSONObject;
import improve.sort.SortAchieve;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SortAchieveImpl implements SortAchieve {
    int[] array = {4, 52, 2, 2, 22, 21};
    int size = array.length;
    @Test
    @Override
    public void bubbleSort() {

        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size - i; j++) {
                if (array[j] > array[j - 1]) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
        System.out.println(JSONObject.toJSON(array));
    }

    @Override
    public void insertSort() {
        for (int i = 0; i < this.size; i++) {
            int tmp = array[i];
            for (int j = i; j < size - i; j--) {
                if (tmp > array[j]) {

                }
            }
        }
    }

}
