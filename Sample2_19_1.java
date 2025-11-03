public class Sample2_19_1 {                                   // 宣告公開類別 Sample2_19_1（檔名需與類別同名）
    private static final int[] SCORES = {80, 60, 22, 50, 75};  // 以常數陣列直接初始化分數（避免魔術數字與重複賦值，Readability）

    public static void main(String[] args) {                   // 主程式進入點（JVM 從此開始執行）
        final int count = SCORES.length;                       // 取出陣列長度並存入區域常數，避免重複呼叫 length、語意更清楚

        for (int i = 0; i < count; i++) {                      // 以索引 for 迴圈遍歷陣列（需要索引以顯示第幾位）
            System.out.printf("第%d個人的分數是%d分%n",         // 使用格式化輸出 printf（較清楚、效能優於多次字串連接）
                              i + 1,                            // 顯示人員序號（從 1 起算）
                              SCORES[i]);                      // 顯示對應索引的分數
        }                                                       // for 迴圈結束

        System.out.printf("考試人數為%d人%n", count);           // 使用同一風格的格式化輸出，印出總人數並換行（%n 跨平台換行）
    }                                                           // main 方法結束
}                                                               // 類別 Sample2_19_1 結束
