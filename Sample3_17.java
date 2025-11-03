public class Sample3_17 {                                                                 // 宣告公開類別 Sample3_17（檔名需與類別同名）
    private static final String DEFAULT_STR = "Hello";                                     // 定義預設字串：若未提供參數則使用此字串

    public static void main(String[] args) {                                              // 主程式進入點（JVM 從此開始執行）
        final String str = (args != null && args.length > 0 && args[0] != null)           // 取命令列第一個參數（若存在且非 null）
                ? args[0]                                                                  // 若有提供參數則使用參數
                : DEFAULT_STR;                                                             // 否則採用預設字串

        if (str.isEmpty()) {                                                               // 防呆：若為空字串則無法存取任何字元
            System.out.println("輸入字串為空。");                                           // 輸出提示訊息
            return;                                                                        // 結束程式，避免後續存取越界
        }                                                                                  // 空字串檢查結束

        if (str.length() < 2) {                                                            // 若 UTF-16 的長度小於 2，無法安全取得第二個 char
            char ch1 = str.charAt(0);                                                      // 仍可安全取得第一個 char
            System.out.printf("%s 的第一個字元是 %c%n", str, ch1);                         // 輸出第一個 char
            System.out.printf("%s 的長度（UTF-16 code units）是 %d%n", str, str.length()); // 說明 length 是 UTF-16 單元數
            int cpCount = str.codePointCount(0, str.length());                             // 計算 Unicode 碼點數（能反映代理對 surrogate）
            System.out.printf("%s 的 Unicode 碼點數是 %d%n", str, cpCount);                // 輸出碼點數量
            return;                                                                        // 提早結束（避免後面存取第 2 個 char）
        }                                                                                  // 長度檢查結束

        char ch1 = str.charAt(0);                                                          // 取得第一個 char（UTF-16 單元）
        char ch2 = str.charAt(1);                                                          // 取得第二個 char（UTF-16 單元）
        int len = str.length();                                                            // 取得字串長度（以 UTF-16 單元為單位）
        int cpCount = str.codePointCount(0, str.length());                                 // 取得字串的 Unicode 碼點數（較貼近實際字元）

        System.out.printf("%s 的第一個字元是 %c%n", str, ch1);                             // 格式化輸出第一個 char
        System.out.printf("%s 的第二個字元是 %c%n", str, ch2);                             // 格式化輸出第二個 char
        System.out.printf("%s 的長度（UTF-16 code units）是 %d%n", str, len);              // 說明 length 的語意是 UTF-16 單元數
        System.out.printf("%s 的 Unicode 碼點數是 %d%n", str, cpCount);                    // 額外輸出碼點數（遇到表情符號會更準確）

        if (cpCount >= 2) {                                                                // 進階：以「碼點」安全取得前兩個實際字元
            int cp1 = str.codePointAt(0);                                                  // 取得第 1 個碼點（可涵蓋 BMP 以外的字元）
            int index2 = Character.offsetByCodePoints(str, 0, 1);                          // 計算第 2 個碼點在字串中的起始索引
            int cp2 = str.codePointAt(index2);                                             // 取得第 2 個碼點
            System.out.printf("以碼點計：第一個字元 U+%04X，第二個字元 U+%04X%n", cp1, cp2); // 以 U+XXXX 形式輸出碼點（十六進位）
        }                                                                                  // 碼點輸出結束
    }                                                                                      // main 方法結束
}                                                                                          // 類別 Sample3_17 結束
