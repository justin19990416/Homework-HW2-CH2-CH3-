public class Sample3_6 {                                      // 宣告公開類別 Sample3_6（檔名需與類別同名）
    public static void main(String[] args) {                  // 主程式進入點（JVM 從這裡開始執行）
        Car car1 = new Car();                                 // 建立一台 Car 物件，使用無參數建構子（初始車號與油量為預設值）
        car1.setNum(1234);                                    // 設定車號為 1234（內含參數驗證與訊息輸出）
        car1.setGas(20.5);                                    // 設定汽油量為 20.5 公升（內含參數驗證與訊息輸出）
    }                                                         // main 方法結束
}                                                             // 類別 Sample3_6 結束

final class Car {                                             // 宣告 Car 類別並標示為 final（此練習不需被繼承，保持簡潔）
    private static final int MIN_NUM = 0;                     // 常數：車號的最小允許值（非負）
    private static final double MIN_GAS = 0.0;                // 常數：汽油量下限（不可為負）
    private static final double MAX_GAS = 100.0;              // 常數：油箱上限（示例值 100 公升，可依實際需求調整）

    private int num;                                          // 私有欄位：車號（封裝以避免被外部任意改動）
    private double gas;                                       // 私有欄位：汽油量（單位：公升）

    public Car() {                                            // 無參數建構子：提供合理初始狀態
        this.num = 0;                                         // 預設車號為 0（表示尚未設定）
        this.gas = 0.0;                                       // 預設汽油量為 0.0 公升
    }                                                         // 無參數建構子結束

    public void setNum(int n) {                               // 設定車號的方法（含基本參數驗證）
        if (n < MIN_NUM) {                                    // 檢查：車號不可小於允許下限
            throw new IllegalArgumentException("車號不得為負數"); // 不合法時拋出參數例外，避免物件進入不一致狀態
        }                                                     // 驗證結束
        this.num = n;                                         // 通過驗證後才寫入欄位
        System.out.printf("將車號設為 %d%n", n);              // 以格式化輸出回饋設定結果（%n 為跨平台換行）
    }                                                         // setNum 方法結束

    public void setGas(double g) {                            // 設定汽油量的方法（含基本參數驗證）
        if (g < MIN_GAS) {                                    // 檢查：汽油量不可為負
            throw new IllegalArgumentException("汽油量不得為負數"); // 不合法時拋出參數例外
        }                                                     // 驗證下限結束
        if (g > MAX_GAS) {                                    // 檢查：汽油量不可超過油箱上限
            throw new IllegalArgumentException(               // 超出上限則拋出參數例外
                "汽油量不可超過油箱上限 " + MAX_GAS + " 公升"); // 錯誤訊息包含上限值，利於除錯
        }                                                     // 驗證上限結束
        this.gas = g;                                         // 通過驗證後寫入欄位
        System.out.printf("將汽油量設為 %.1f 公升%n", g);       // 以格式化輸出回饋設定結果（顯示 1 位小數）
    }                                                         // setGas 方法結束

    public int getNum() {                                     // 取得車號（提供唯讀存取以維持封裝）
        return num;                                           // 回傳目前車號
    }                                                         // getNum 方法結束

    public double getGas() {                                  // 取得汽油量（提供唯讀存取以維持封裝）
        return gas;                                           // 回傳目前汽油量
    }                                                         // getGas 方法結束
}                                                             // 類別 Car 結束

