public class Sample3_10_1 {                                      // 宣告公開類別 Sample3_10_1（檔名需與類別同名）
    public static void main(String[] args) {                   // 主程式進入點（JVM 從這裡開始執行）
        Car car1 = new Car();                                  // 建立一台 Car 物件（使用無參數建構子給預設值）

        car1.setNumGas(1234, 20.5);                            // 一次設定車號與汽油量（方法內先驗證，再同時更新，避免部分更新）
        car1.show();                                           // 顯示目前車輛狀態（車號與汽油量）

        System.out.println("嘗試將汽油量設為 -10.0");            // 說明接下來要嘗試設定不合法的汽油量（示範錯誤處理）
        try {                                                  // 使用 try-catch 捕捉參數不合法所拋出的例外
            car1.setNumGas(car1.getNum(), -10.0);              // 嘗試以現有車號搭配非法油量更新（預期拋出 IllegalArgumentException）
        } catch (IllegalArgumentException e) {                 // 捕捉參數不合法例外（IllegalArgumentException）
            System.err.println("設定失敗：" + e.getMessage());    // 於標準錯誤輸出詳細原因（Observability）
        }                                                      // try-catch 區塊結束

        car1.show();                                           // 再次顯示車輛狀態，確認前一次錯誤未造成部分更新
    }                                                          // main 方法結束
}                                                              // 類別 Sample3_10 結束

final class Car {                                              // 宣告 Car 類別並標示為 final（本範例不需被繼承，保持簡潔）
    private static final int MIN_NUM = 0;                      // 常數：車號下限（不可為負）
    private static final double MIN_GAS_EXCLUSIVE = 0.0;       // 常數：汽油量開區間下限（必須大於 0）
    private static final double MAX_GAS_EXCLUSIVE = 100.0;     // 常數：汽油量開區間上限（必須小於 100）

    private int num;                                           // 私有欄位：車號（封裝資料，避免外部直接改動）
    private double gas;                                        // 私有欄位：汽油量（單位：公升）

    public Car() {                                             // 無參數建構子：提供合理初始狀態
        this.num = 0;                                          // 預設車號為 0（代表尚未設定）
        this.gas = 0.0;                                        // 預設汽油量為 0.0 公升
    }                                                          // 無參數建構子結束

    public int getNum() {                                      // 取得車號（getter，維持封裝）
        return num;                                            // 回傳目前車號
    }                                                          // getNum 方法結束

    public double getGas() {                                   // 取得汽油量（getter，維持封裝）
        return gas;                                            // 回傳目前汽油量
    }                                                          // getGas 方法結束

    public void setNum(int n) {                                // 單獨設定車號（含基本參數驗證）
        if (n < MIN_NUM) {                                     // 檢查：車號不可為負數
            throw new IllegalArgumentException("車號不得為負數"); // 不合法時丟出參數例外（IllegalArgumentException）
        }                                                      // 驗證通過
        this.num = n;                                          // 指派合法值到欄位
        System.out.printf("將車號設為 %d%n", n);               // 以格式化輸出回饋設定結果（%n 跨平台換行）
    }                                                          // setNum 方法結束

    public void setGas(double g) {                             // 單獨設定汽油量（含範圍驗證）
        if (!(g > MIN_GAS_EXCLUSIVE && g < MAX_GAS_EXCLUSIVE)) { // 檢查：必須滿足 0 < g < 100 的開區間條件
            throw new IllegalArgumentException(                // 不合法則丟出參數例外
                String.format("汽油量需介於 %.1f 與 %.1f 之間（不含）", // 清楚說明允許範圍（exclusive）
                              MIN_GAS_EXCLUSIVE, MAX_GAS_EXCLUSIVE)); // 代入上下限
        }                                                      // 驗證通過
        this.gas = g;                                          // 指派合法值到欄位
        System.out.printf("將汽油量設為 %.1f 公升%n", g);        // 以格式化輸出回饋設定結果（保留 1 位小數）
    }                                                          // setGas 方法結束

    public void setNumGas(int n, double g) {                   // 一次性設定車號與汽油量（atomic-like：先驗證後更新）
        // 先「全數驗證」，通過後再更新，避免只改到其中一個造成狀態不一致（partial update）         // 設計說明
        if (n < MIN_NUM) {                                     // 檢查車號：不可為負
            throw new IllegalArgumentException("車號不得為負數"); // 不合法則丟出例外
        }                                                      // 車號驗證通過
        if (!(g > MIN_GAS_EXCLUSIVE && g < MAX_GAS_EXCLUSIVE)) { // 檢查油量：必須 0 < g < 100
            throw new IllegalArgumentException(                // 不合法則丟出例外
                String.format("汽油量需介於 %.1f 與 %.1f 之間（不含）",
                              MIN_GAS_EXCLUSIVE, MAX_GAS_EXCLUSIVE)); // 提示有效範圍
        }                                                      // 油量驗證通過
        this.num = n;                                          // 同步更新：指派合法車號
        this.gas = g;                                          // 同步更新：指派合法汽油量
        System.out.printf("將車號設為 %d，汽油量設為 %.1f 公升%n", n, g); // 以格式化輸出回饋設定結果
    }                                                          // setNumGas 方法結束

    public void show() {                                       // 顯示車輛目前狀態的方法（對使用者輸出）
        System.out.printf("車號是 %d%n", this.num);            // 顯示車號（printf 可讀性佳）
        System.out.printf("汽油量是 %.1f 公升%n", this.gas);   // 顯示汽油量（保留 1 位小數並附上單位）
    }                                                          // show 方法結束
}                                                              // 類別 Car 結束
