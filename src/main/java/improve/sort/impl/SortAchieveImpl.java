package improve.sort.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.inject.internal.cglib.core.$KeyFactory;
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

    @Test
    @Override
    public void insertSort() {

        /*
         * ** 4, 52, 2, 2, 22, 21**
         * */

        for (int i = 0; i < size - 1; i++) {
            int temp = array[i + 1]; // 22
            for (int j = i; j >= 0; j--) {
                if (temp < array[j]) { //
                    array[j + 1] = array[j];
                    array[j] = temp; //
                }
            }
        }
        System.out.println(JSONObject.toJSONString(array));
    }

    @Test
    @Override
    public void sectionSort() {

        //always find the max or min number in the last parts
        for (int i = 0; i < size; i++) {
            int minindex = i;
            for (int j = i; j <size; j++) {
                if (array[j] > array[minindex]) {
                    minindex = j;
                }
            }
            int temp = array[minindex];
            array[minindex] = array[i];
            array[i] = temp;
        }
        System.out.println(JSONObject.toJSONString(array));
    }

    @Override
    public void shellSort() {
        //设置增量,增量递减,当为1时,




    }

    @Override
    public void quickSort() {

    }

    @Override
    public void mergeSort() {

    }

    
    /**
    * @desc:
    * @date: 2020/3/3
    **/
    
    
    
}
