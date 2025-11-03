public class Sample3_12_1 {                              // 宣告公開類別 Sample3_12_1（檔名需與類別同名）
    public static void main(String[] args) {              // 主程式進入點（JVM 從這裡開始執行）
        Car car1 = new Car();                             // 建立一台 Car 物件，使用無參數建構子（會印出「生產了車子」）
        car1.show();                                      // 呼叫成員方法顯示車輛目前狀態（車號與汽油量）
    }                                                     // main 方法結束
}                                                         // 類別 Sample3_12_1 結束

final class Car {                                         // 宣告 Car 類別並標示為 final（此範例不需被繼承，維持簡潔）
    private static final int MIN_NUM = 0;                 // 常數：車號下限（不可為負值）
    private static final double MIN_GAS = 0.0;            // 常數：汽油量下限（不可為負）
    private static final double MAX_GAS = 100.0;          // 常數：油箱上限（示例值，可依實際需求調整）

    private int num;                                      // 私有欄位：車號（封裝以避免外部任意改動）
    private double gas;                                   // 私有欄位：汽油量（單位：公升）

    public Car() {                                        // 無參數建構子：提供合理初始狀態
        this(0, 0.0);                                     // 透過 this(...) 委派到完整建構子（集中初始化與訊息輸出）
    }                                                     // 無參數建構子結束

    public Car(int num, double gas) {                     // 完整建構子：建立物件時直接指定車號與汽油量
        if (num < MIN_NUM) {                              // 檢查：車號不可小於下限
            throw new IllegalArgumentException("車號不得為負數"); // 不合法則拋出參數例外，避免不一致狀態
        }                                                 // 車號檢查結束
        if (gas < MIN_GAS || gas > MAX_GAS) {             // 檢查：汽油量需落在允許範圍內
            throw new IllegalArgumentException(           // 超出範圍則拋出參數例外
                String.format("汽油量需介於 %.1f 與 %.1f 公升之間", MIN_GAS, MAX_GAS));
        }                                                 // 汽油量檢查結束
        this.num = num;                                   // 指派通過驗證的車號
        this.gas = gas;                                   // 指派通過驗證的汽油量
        System.out.println("生產了車子");                  // 建構完成後輸出提示（維持題目原有行為）
    }                                                     // 完整建構子結束

    public void show() {                                  // 成員方法：顯示目前車輛狀態
        System.out.printf("車號是 %d%n", this.num);       // 使用格式化輸出車號（%n 為跨平台換行）
        System.out.printf("汽油量是 %.1f 公升%n", this.gas); // 使用格式化輸出汽油量（保留 1 位小數並標示單位）
    }                                                     // show 方法結束

    public int getNum() {                                 // 取得車號（getter，維持封裝）
        return num;                                       // 回傳目前車號
    }                                                     // getNum 方法結束

    public double getGas() {                              // 取得汽油量（getter，維持封裝）
        return gas;                                       // 回傳目前汽油量
    }                                                     // getGas 方法結束

    @Override                                             // 覆寫 Object.toString()：提供友善的摘要字串
    public String toString() {                            // 將物件狀態轉為可讀字串（便於除錯/記錄）
        return String.format("Car{num=%d, gas=%.1f}", num, gas); // 回傳包含車號與汽油量的摘要資訊
    }                                                     // toString 方法結束
}                                                         // 類別 Car 結束

