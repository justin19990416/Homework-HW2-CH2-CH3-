public class Sample3_11_1 {                                      // 宣告公開類別 Sample3_11_1（檔名需與類別同名）
    public static void main(String[] args) {                      // 主程式進入點（JVM 從這裡開始執行）
        Car car1 = new Car();                                     // 建立一台 Car 物件（使用無參數建構子給預設值）

        car1.setCar(1234, 20.5);                                  // 一次設定車號與汽油量（核心方法，含完整驗證）
        car1.show();                                              // 顯示目前車輛狀態（車號與汽油量）

        System.out.println("只要變更車號");                         // 提示：接下來僅變更車號，汽油量保持不變
        car1.setCar(2345);                                        // 使用多載 setCar(int)：只更新車號（內部委派至核心方法）
        car1.show();                                              // 再次顯示，確認車號已更新、汽油量未變

        System.out.println("只要變更汽油量");                       // 提示：接下來僅變更汽油量，車號保持不變
        car1.setCar(30.5);                                        // 使用多載 setCar(double)：只更新汽油量（內部委派至核心方法）
        car1.show();                                              // 再次顯示，確認汽油量已更新、車號未變
    }                                                             // main 方法結束
}                                                                 // 類別 Sample3_11_1 結束

final class Car {                                                // 宣告 Car 類別並標示為 final（此範例不需被繼承，保持簡潔）
    private static final int MIN_NUM = 0;                        // 常數：車號允許的最小值（非負）
    private static final double MIN_GAS_EXCLUSIVE = 0.0;         // 常數：汽油量開區間下限（必須大於 0）
    private static final double MAX_GAS_EXCLUSIVE = 100.0;       // 常數：汽油量開區間上限（必須小於 100）

    private int num;                                             // 私有欄位：車號（封裝，避免外部任意改動）
    private double gas;                                          // 私有欄位：汽油量（單位：公升）

    public Car() {                                               // 無參數建構子：提供合理初始狀態
        this.num = 0;                                            // 初始車號設為 0（表示尚未設定）
        this.gas = 0.0;                                          // 初始汽油量設為 0.0 公升
    }                                                            // 無參數建構子結束

    public int getNum() {                                        // 取得車號的存取子（getter）
        return num;                                              // 回傳目前車號
    }                                                            // getNum 方法結束

    public double getGas() {                                     // 取得汽油量的存取子（getter）
        return gas;                                              // 回傳目前汽油量
    }                                                            // getGas 方法結束

    public void setCar(int n) {                                  // 多載方法：只更新車號（保持汽油量不變）
        setCar(n, this.gas);                                     // 委派至核心 setCar(int,double) 以共用驗證與更新邏輯
    }                                                            // setCar(int) 方法結束

    public void setCar(double g) {                               // 多載方法：只更新汽油量（保持車號不變）
        setCar(this.num, g);                                     // 委派至核心 setCar(int,double) 以共用驗證與更新邏輯
    }                                                            // setCar(double) 方法結束

    public void setCar(int n, double g) {                        // 核心多載方法：同時設定車號與汽油量（先驗證、後更新）
        if (n < MIN_NUM) {                                       // 檢查：車號不可小於允許下限
            throw new IllegalArgumentException("車號不得為負數");  // 不合法則拋出參數例外，避免不一致狀態
        }                                                        // 車號檢查結束
        if (!(g > MIN_GAS_EXCLUSIVE && g < MAX_GAS_EXCLUSIVE)) { // 檢查：汽油量必須滿足 0 < g < 100 的開區間
            throw new IllegalArgumentException(                  // 不合法則拋出參數例外
                String.format("汽油量需介於 %.1f 與 %.1f 之間（不含）",
                              MIN_GAS_EXCLUSIVE, MAX_GAS_EXCLUSIVE)); // 清楚說明允許範圍（exclusive）
        }                                                        // 汽油量檢查結束
        this.num = n;                                            // 通過驗證後寫入車號
        this.gas = g;                                            // 通過驗證後寫入汽油量
        System.out.printf("將車號設為 %d，汽油量設為 %.1f 公升%n", n, g); // 以格式化輸出回饋設定結果
    }                                                            // setCar(int,double) 方法結束

    public void show() {                                         // 顯示目前車輛狀態的方法（對使用者輸出）
        System.out.printf("車號是 %d%n", this.num);              // 顯示車號（printf 可讀性佳，%n 跨平台換行）
        System.out.printf("汽油量是 %.1f 公升%n", this.gas);     // 顯示汽油量（保留 1 位小數並標示單位）
    }                                                            // show 方法結束

    @Override                                                    // 覆寫 Object.toString()：提供友善摘要字串
    public String toString() {                                   // 將物件狀態轉為易讀字串（方便除錯/記錄）
        return String.format("Car{num=%d, gas=%.1f}", num, gas); // 以格式化方式回傳車號與汽油量的摘要
    }                                                            // toString 方法結束
}                                                                // 類別 Car 結束

