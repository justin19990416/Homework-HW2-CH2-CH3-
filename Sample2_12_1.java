public class Sample2_12_1 {                                     // 宣告公開類別 Sample2_12_1（檔名需與類別同名）
    private static final int OUTER_COUNT = 5;                    // 定義常數：外層迴圈執行次數（提升可讀性、避免魔術數字）
    private static final int INNER_COUNT = 3;                    // 定義常數：內層迴圈執行次數（集中管理、易於日後調整）

    public static void main(String[] args) {                     // 主程式進入點（JVM 從這裡開始執行）
        for (int i = 0; i < OUTER_COUNT; i++) {                  // 外層 for 迴圈：i 從 0 迭代到 OUTER_COUNT-1
            for (int j = 0; j < INNER_COUNT; j++) {              // 內層 for 迴圈：j 從 0 迭代到 INNER_COUNT-1
                System.out.printf("i圈 %d；j是 %d%n", i, j);     // 格式化輸出目前的 i 與 j，%n 為跨平台換行（可讀性佳）
            }                                                    // 內層迴圈結束
        }                                                        // 外層迴圈結束
    }                                                            // main 方法結束
}                                                                // 類別 Sample2_12_1 結束

