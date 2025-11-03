public class Sample3_9_1 {                                      // 宣告公開類別 Sample3_9_1（檔名需與類別同名）
    public static void main(String[] args) {                    // 主程式進入點（JVM 從這裡開始執行）
        Car car1 = new Car();                                   // 建立一台 Car 物件（使用無參數建構子給預設值）

        car1.setNum(1234);                                      // 設定車號為 1234（由 setter 做基本驗證）

        try {                                                   // 嘗試區塊：示範輸入不合法汽油量時的處理
            car1.setGas(-10);                                   // 嘗試將汽油量設為 -10（不合法，預期拋出例外）
        } catch (IllegalArgumentException e) {                  // 捕捉參數不合法例外（IllegalArgumentException）
            System.err.println("設定汽油量失敗：" + e.getMessage()); // 顯示錯誤原因到標準錯誤輸出（Observability）
            car1.setGas(10.0);                                  // 回復為合法值：將汽油量改為 10.0 公升
        }                                                       // try-catch 結束

        car1.show();                                            // 顯示車輛目前狀態（車號與汽油量）
    }                                                           // main 方法結束
}                                                               // 類別 Sample3_9_1 結束

final class Car {                                               // 宣告 Car 類別並標示為 final（此範例不需被繼承，保持簡潔）
    private static final int MIN_NUM = 0;                       // 常數：車號下限（非負值）
    private static final double MIN_GAS = 0.0;                  // 常數：汽油量下限（不可為負）
    private static final double MAX_GAS = 100.0;                // 常數：油箱上限（示例值，可依實際需求調整）

    private int num;                                            // 私有欄位：車號（封裝，避免外部直接改動）
    private double gas;                                         // 私有欄位：汽油量（單位：公升）

    public Car() {                                              // 無參數建構子：提供合理的初始狀態
        this.num = 0;                                           // 初始車號設為 0（表示尚未設定）
        this.gas = 0.0;                                         // 初始汽油量設為 0.0 公升
    }                                                           // 建構子結束

    public void setNum(int n) {                                 // 設定車號的 setter（含基本參數驗證）
        if (n < MIN_NUM) {                                      // 檢查：車號不可小於下限
            throw new IllegalArgumentException("車號不得為負數"); // 不合法則拋出參數例外（IllegalArgumentException）
        }                                                       // 驗證通過
        this.num = n;                                           // 將合法車號寫入欄位
        System.out.printf("將車號設為 %d%n", n);                // 以格式化輸出回饋設定結果（%n 跨平台換行）
    }                                                           // setNum 方法結束

    public void setGas(double g) {                              // 設定汽油量的 setter（含上下限驗證）
        if (g < MIN_GAS) {                                      // 檢查：汽油量不可為負
            throw new IllegalArgumentException("汽油量不得為負數"); // 不合法則拋出參數例外
        }                                                       // 下限驗證通過
        if (g > MAX_GAS) {                                      // 檢查：汽油量不可超過油箱上限
            throw new IllegalArgumentException(                 // 超出上限亦拋出參數例外
                "汽油量不可超過油箱上限 " + MAX_GAS + " 公升");   // 提供上限值以利使用者修正
        }                                                       // 上限驗證通過
        this.gas = g;                                           // 將合法汽油量寫入欄位
        System.out.printf("將汽油量設為 %.1f 公升%n", g);         // 以格式化輸出回饋設定結果（保留 1 位小數）
    }                                                           // setGas 方法結束

    public int getNum() {                                       // 取得車號（getter，維持封裝）
        return num;                                             // 回傳目前車號
    }                                                           // getNum 方法結束

    public double getGas() {                                    // 取得汽油量（getter，維持封裝）
        return gas;                                             // 回傳目前汽油量
    }                                                           // getGas 方法結束

    public void show() {                                        // 顯示車輛狀態的方法（對使用者輸出資訊）
        System.out.printf("車號是 %d%n", this.num);             // 顯示車號（使用 printf 可讀性佳）
        System.out.printf("汽油量是 %.1f 公升%n", this.gas);    // 顯示汽油量（保留 1 位小數並標示單位）
    }                                                           // show 方法結束

    @Override                                                   // 覆寫 Object.toString()：提供友善的摘要字串
    public String toString() {                                  // 將物件狀態轉為易讀字串（便於偵錯/記錄）
        return String.format("Car{num=%d, gas=%.1f}", num, gas); // 車輛資訊摘要（車號與汽油量）
    }                                                           // toString 方法結束
}                                                               // 類別 Car 結束

