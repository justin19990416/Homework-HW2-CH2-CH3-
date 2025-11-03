public class Sample3_15_1 {                                      // 宣告公開類別 Sample3_15_1（檔名需與類別同名）
    public static void main(String[] args) {                      // 主程式進入點（JVM 從這裡開始執行）
        Car.showSum();                                           // 呼叫「類別方法」先顯示目前累計車輛數（尚未建立任何車）

        Car car1 = new Car();                                    // 建立第一台車（呼叫無參數建構子，自動累計總數 +1）
        car1.setCar(1234, 20.5);                                 // 設定車號與汽油量（內含參數驗證，先驗證後更新）
        Car.showSum();                                           // 再次顯示總數（此時應為 1）

        Car car2 = new Car();                                    // 建立第二台車（再次累計總數 +1）
        car2.setCar(4567, 30.5);                                 // 設定第二台車的車號與汽油量
        Car.showSum();                                           // 顯示總數（此時應為 2）
    }                                                            // main 方法結束
}                                                                // 類別 Sample3_15_1 結束

final class Car {                                                // 宣告 Car 類別並標示為 final（此練習不需被繼承，保持簡潔）
    private static int count = 0;                                // 類別變數：目前已建立的 Car 物件總數（所有物件共享）
    private static final double MIN_GAS = 0.0;                   // 常數：汽油量下限（不可為負）
    private static final double MAX_GAS = 100.0;                 // 常數：汽油量上限（示例值，可依需求調整）

    private int num;                                             // 物件欄位：車號（封裝，避免外部直接改動）
    private double gas;                                          // 物件欄位：汽油量（單位：公升）

    public Car() {                                               // 無參數建構子：建立物件時設定預設狀態並累計總數
        this.num = 0;                                            // 預設車號為 0（代表尚未設定）
        this.gas = 0.0;                                          // 預設汽油量為 0.0 公升
        count++;                                                 // 類別計數器加一（追蹤已生產的車輛數）
        System.out.println("生產了車子");                         // 與題目一致：建立時輸出提示訊息
    }                                                            // 無參數建構子結束

    public void setCar(int n, double g) {                        // 物件方法：同時設定車號與汽油量（先驗證、後更新）
        if (n < 0) {                                             // 檢查：車號不得為負數
            throw new IllegalArgumentException("車號不得為負數"); // 不合法則拋出參數例外，避免物件進入不一致狀態
        }                                                        // 車號檢查結束
        if (g < MIN_GAS || g > MAX_GAS) {                        // 檢查：汽油量需介於 [MIN_GAS, MAX_GAS] 範圍內
            throw new IllegalArgumentException(                  // 超出範圍則拋出參數例外
                String.format("汽油量需介於 %.1f 與 %.1f 公升之間", MIN_GAS, MAX_GAS)); // 清楚告知合法區間
        }                                                        // 汽油量檢查結束
        this.num = n;                                            // 通過驗證後：更新車號
        this.gas = g;                                            // 通過驗證後：更新汽油量
        System.out.printf("將車號設為 %d，汽油量設為 %.1f 公升%n", num, gas); // 以格式化輸出回饋設定結果
    }                                                            // setCar 方法結束

    public static void showSum() {                               // 類別方法：顯示目前所有 Car 的總數
        System.out.printf("車子總共有 %d 台%n", count);           // 直接存取類別變數 count 並輸出（%n 跨平台換行）
    }                                                            // showSum 方法結束

    public void show() {                                         // 物件方法：顯示當前車輛的詳細資訊
        System.out.printf("車號是 %d%n", this.num);              // 輸出車號（使用 printf 提升可讀性）
        System.out.printf("汽油量是 %.1f 公升%n", this.gas);     // 輸出汽油量（保留 1 位小數並附單位）
    }                                                            // show 方法結束
}                                                                // 類別 Car 結束
