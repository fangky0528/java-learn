package com.cipher.algorithm_visualization.E_Sort.merge;

import com.cipher.algorithm_visualization.A_Base.AlgoFrame;
import com.cipher.algorithm_visualization.A_Base.AlgoVisHelper;
import com.cipher.algorithm_visualization.A_Base.AlgoVisualizer;

import java.util.Arrays;

/**
 * @Author: CipherCui
 * @Description:
 * @Date: Created in 15:06 2018/9/18
 */
public class MergeSortVisualizer extends AlgoVisualizer {

    private static final int N = 100;
    private static final int DELAY = 50;

    public MergeSortVisualizer(int sceneWidth, int sceneHeight) {
        super(sceneWidth, sceneHeight);
    }

    @Override
    public Object initData(int sceneWidth, int sceneHeight) {
        return new MergeSortData(N, sceneHeight);
    }

    @Override
    public AlgoFrame initFrame(int sceneWidth, int sceneHeight) {
        return new MergeSortFrame("Merge Sort", sceneWidth, sceneHeight);
    }

    @Override
    public void run(Object data, AlgoFrame frame) {
        MergeSortData sortData = (MergeSortData) getData();
        setData(-1, -1, -1);
        mergeSort(0, sortData.N() - 1);
        setData(0, sortData.N() - 1, sortData.N() - 1);
    }

    private void mergeSort(int l, int r) {
        if (l >= r) {
            return;
        }
        setData(l, r, -1);
        int mid = (l + r) / 2;
        mergeSort(l, mid);
        mergeSort(mid + 1, r);
        merge(l, mid, r);
    }

    private void merge(int l, int mid, int r) {
        MergeSortData data = (MergeSortData) getData();
        int[] aux = Arrays.copyOfRange(data.numbers, l, r + 1);
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                data.numbers[k] = aux[j - l];
                j++;
            } else if (j > r) {
                data.numbers[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {
                data.numbers[k] = aux[i - l];
                i++;
            } else {
                data.numbers[k] = aux[j - l];
                j++;
            }
            setData(l, r, k);
        }
    }

    private void setData(int l, int r, int mergeIndex) {
        MergeSortData data = (MergeSortData) getData();
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;
        getFrame().render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        new MergeSortVisualizer(800, 300);
    }

}
