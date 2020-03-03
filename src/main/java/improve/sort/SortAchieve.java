package improve.sort;
/**
* 
*@className: 
*@author: 
*@date:  
* 
**/
public interface SortAchieve {
    //TODO 插入排序：直接排序，希尔排序
    //TODO 选择排序：简单选择排序，堆排序
    //TODO 交换排序：冒泡排序，快速排序
    //TODO 归并排序
    //TODO 基数排序
    //TODO 外排序

    /**
    * @desc: 冒泡排序，相邻元素比较，若值大或小，则交换位置.
    * @date:
    **/
    void bubbleSort();
    /**
    * @desc: 插入排序
    * @date: 2020/3/3
    **/
    void insertSort();
    /**
    * @desc: 选择排序
    * @date: 2020/3/3
    **/
    void sectionSort();

    /**
    * @desc: 希尔排序
    * @date: 2020/3/3
    **/
    void shellSort();

    /**
    * @desc: 快排序
    * @date: 2020/3/3
    **/
    void quickSort();

    /**
    * @desc: 归并排序
    * @date: 2020/3/3
    **/
    void mergeSort();
}
