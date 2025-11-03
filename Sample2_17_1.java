public class Sample2_17_1 {                                                        // 宣告公開類別 Sample2_17_1（檔名需與類別同名）
    private static final int[] SCORES = {80, 60, 22, 50, 75};                       // 以常數陣列直接初始化分數，避免魔術數字與重複賦值

    public static void main(String[] args) {                                        // 主程式進入點（JVM 從這裡開始執行）
        final int invalidIndex = 10;                                                // 範例中的「錯誤索引」，用常數表達意圖更清楚
        final int replacement = 75;                                                 // 準備要寫入的替換分數

        if (isInBounds(invalidIndex, SCORES.length)) {                              // 先做邊界檢查：若索引在合法範圍內才寫入
            SCORES[invalidIndex] = replacement;                                     // 寫入替換分數（僅在合法索引時執行）
        } else {                                                                    // 否則代表索引越界
            System.err.printf(                                                      // 使用錯誤輸出串流提示問題（提升可觀測性）
                "索引 %d 超出範圍，合法範圍為 [0, %d)%n", invalidIndex, SCORES.length // 顯示實際索引與合法範圍
            );                                                                      // 錯誤訊息列印結束
            // 若想示範捕捉例外，可改用 try-catch 進行 ArrayIndexOutOfBoundsException 的示範               // 說明：這裡選擇預防性檢查而非故意觸發例外
        }                                                                           // 邊界檢查結束

        for (int i = 0; i < SCORES.length; i++) {                                   // 使用 length 作為上限，避免寫死迴圈次數
            System.out.printf("第%d個人的分數是%d分%n", i + 1, SCORES[i]);           // 格式化輸出每位同學的分數；%n 為跨平台換行
        }                                                                           // 迴圈結束，所有分數已輸出
    }                                                                               // main 方法結束

    private static boolean isInBounds(int index, int length) {                      // 小工具方法：封裝索引邊界檢查（提升可讀性與重用性）
        return index >= 0 && index < length;                                        // 當索引在 [0, length) 範圍內回傳 true，否則 false
    }                                                                               // isInBounds 方法結束
}                                                                                   // 類別 Sample2_17_1 結束
