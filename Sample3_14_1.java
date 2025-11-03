public class Sample3_14_1 {                                  // 宣告公開類別 Sample3_14_1（檔名需與類別同名）
    public static void main(String[] args) {                  // 主程式進入點（JVM 從此開始執行）
        Car car1 = new Car();                                 // 建立一台預設車（無參數建構子：車號 0、油量 0.0，並輸出訊息）
        car1.show();                                          // 顯示 car1 目前狀態（車號與汽油量）

        Car car2 = new Car(1234, 25.0);                       // 建立一台指定車號與油量的車（參數建構子含驗證與輸出）
        car2.show();                                          // 顯示 car2 目前狀態（車號與汽油量）
    }                                                         // main 方法結束
}                                                             // 類別 Sample3_14_1 結束

final class Car {                                             // 宣告 Car 類別並標示為 final（此範例不需被繼承，保持簡潔）
    private static final int MIN_NUM = 0;                     // 常數：車號下限（不可為負）
    private static final double MIN_GAS = 0.0;                // 常數：汽油量下限（不可為負）
    private static final double MAX_GAS = 100.0;              // 常數：油箱上限（示例值，可依實際需求調整）

    private int num;                                          // 私有欄位：車號（封裝以避免外部任意改動）
    private double gas;                                       // 私有欄位：汽油量（單位：公升）

    public Car() {                                            // 無參數建構子：提供合理初始狀態並對使用者提示
        this.num = 0;                                         // 將車號初始化為 0（視為尚未設定）
        this.gas = 0.0;                                       // 將汽油量初始化為 0.0 公升
        System.out.println("生產了車子");                      // 與題目一致：建立預設車時輸出訊息
    }                                                         // 無參數建構子結束

    public Car(int n, double g) {                             // 參數建構子：建立時直接指定車號與汽油量
        this();                                               // 呼叫無參數建構子（必須在第一行，沿用其初始化與訊息）
        validateNum(n);                                       // 檢核車號是否合規（不可為負）
        validateGas(g);                                       // 檢核汽油量是否在允許範圍內
        this.num = n;                                         // 通過檢核後指派車號
        this.gas = g;                                         // 通過檢核後指派汽油量
        System.out.printf("生產了車號為 %d，汽油量為 %.1f 的車子%n", n, g); // 以格式化輸出建立完成的詳細資訊
    }                                                         // 參數建構子結束

    private static void validateNum(int n) {                  // 私有工具方法：檢核車號
        if (n < MIN_NUM) {                                    // 若車號小於下限
            throw new IllegalArgumentException("車號不得為負數"); // 拋出參數例外避免不一致狀態
        }                                                     // 檢核結束
    }                                                         // validateNum 方法結束

    private static void validateGas(double g) {               // 私有工具方法：檢核汽油量
        if (g < MIN_GAS || g > MAX_GAS) {                     // 若油量不在 [0, 100] 範圍內
            throw new IllegalArgumentException(               // 拋出參數例外並說明合法區間
                String.format("汽油量需介於 %.1f 與 %.1f 公升之間", MIN_GAS, MAX_GAS));
        }                                                     // 檢核結束
    }                                                         // validateGas 方法結束

    public void show() {                                      // 成員方法：顯示目前車輛狀態
        System.out.printf("車號是 %d%n", this.num);           // 使用格式化輸出車號（%n 為跨平台換行）
        System.out.printf("汽油量是 %.1f 公升%n", this.gas);  // 使用格式化輸出汽油量（保留 1 位小數並標示單位）
    }                                                         // show 方法結束

    public int getNum() {                                     // Getter：取得車號（維持封裝）
        return num;                                           // 回傳目前車號
    }                                                         // getNum 方法結束

    public double getGas() {                                  // Getter：取得汽油量（維持封裝）
        return gas;                                           // 回傳目前汽油量
    }                                                         // getGas 方法結束

    @Override                                                 // 覆寫 Object.toString() 以提供友善摘要
    public String toString() {                                // 將物件狀態轉為可讀字串（利於除錯/記錄）
        return String.format("Car{num=%d, gas=%.1f}", num, gas); // 回傳摘要資訊：車號與汽油量
    }                                                         // toString 方法結束
}                                                             // 類別 Car 結束

