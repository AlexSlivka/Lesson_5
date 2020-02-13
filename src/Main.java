public class Main {
    public static void main(String[] args) {
        runOneThread();
        runTwoThread();
    }
    
    //Однопоточный метод расчета
    public static void runOneThread() {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        for (float ar : arr) {
            ar = 1f;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("Время работы однопоточного метода = " + (timeEnd - a));
    }

    //Двухпоточный метод расчета
    public static void runTwoThread() {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        for (float ar : arr) {
            ar = 1f;
        }
        long a = System.currentTimeMillis();
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        t1.start();
        t2.start();

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        long timeEnd = System.currentTimeMillis();
        System.out.println("Время работы двухпоточного метода = " + (timeEnd - a));
    }
}
