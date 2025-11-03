import java.lang.reflect.Array;                              // 匯入反射用的 Array 類別：可用來動態建立/存取陣列（Reflection API）

public class Sample2_16_1 {                                  // 宣告公開類別 Sample2_16_1（檔名需與類別同名）
    private static final int LENGTH = 3;                      // 定義常數：陣列長度，集中管理避免魔術數字（magic number）
    private static final int[] INITIAL_VALUES = {123,456,789};// 定義欲寫入的初始值清單（示範資料）

    public static void main(String[] args) {                  // 主程式進入點（JVM 從這裡開始執行）
        final int[] intArray = (int[])                        // 將反射建立的物件轉型為 int[]（型別已知安全）
                Array.newInstance(int.class, LENGTH);         // 使用反射動態建立一個指定長度的 int 陣列

        for (int i = 0; i < Math.min(LENGTH, INITIAL_VALUES.length); i++) { // 以邊界保護（min）逐一填入資料，避免越界
            Array.setInt(intArray, i, INITIAL_VALUES[i]);     // 透過反射的 setInt 寫入 primitive int（避免自動裝箱/拆箱）
        }                                                     // for 迴圈結束，寫入完成

        for (int i = 0; i < intArray.length; i++) {           // 走訪整個陣列以讀出並顯示內容
            int value = Array.getInt(intArray, i);            // 透過反射的 getInt 讀取 primitive int（避免回傳 Integer 物件）
            System.out.printf("intArray[%d] = %d%n", i, value); // 使用格式化輸出顯示索引與值（%n 為跨平台換行）
        }                                                     // for 迴圈結束，輸出完成
    }                                                         // main 方法結束
}                                                             // 類別 Sample2_16_1 結束

