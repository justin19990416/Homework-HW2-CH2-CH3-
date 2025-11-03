public class Sample2_11_1 {                                   // 宣告公開類別 Sample2_11（檔名需與類別同名）
    private static final int LOOP_COUNT = 5;                 // 定義常數：總迴圈次數，集中管理避免魔術數字（magic number）

    public static void main(String[] args) {                 // 主程式進入點（JVM 從這裡開始執行）
        int i = 1;                                           // 設定計數器起始值為 1

        if (LOOP_COUNT <= 0) {                               // 防呆檢查：若迴圈次數小於等於 0，則不執行迴圈
            System.out.println("迴圈次數為 0，無需執行");     // 告知使用者目前無需執行任何迴圈
            return;                                          // 直接結束主程式，避免不必要流程
        }                                                     // if 區塊結束

        do {                                                  // do-while 迴圈：至少會先執行一次迴圈主體
            System.out.printf("第%d次的迴圈%n", i);           // 使用格式化輸出：%d 代入 i，%n 為跨平台換行（Readability/Portability）
            i++;                                             // 計數器遞增 1，準備進入下一次迴圈
        } while (i <= LOOP_COUNT);                            // 迴圈條件：當 i 小於等於 LOOP_COUNT 時持續執行

        System.out.println("迴圈結束");                      // 迴圈完成後的收尾訊息，提升使用者體驗（UX）
    }                                                         // main 方法結束
}                                                             // 類別 Sample2_11 結束
