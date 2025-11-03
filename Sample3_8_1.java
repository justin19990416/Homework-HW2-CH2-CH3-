public class Sample3_8_1 {                                   // 宣告公開類別 Sample3_8_1（修正原始名稱的空白錯誤，檔名需與類別同名）
    public static void main(String[] args) {                  // 主程式進入點（JVM 從這裡開始執行）
        Car car1 = new Car();                                 // 建立一台 Car 物件（使用無參數建構子給預設值）
                                                               // （亦可使用 new Car(1234, 20.5) 直接指定初始值）
        car1.setNumGas(1234, 20.5);                           // 一次設定車號與汽油量（方法內含基本參數驗證與回饋）

        int number = car1.getNum();                           // 透過 getter 取得車號（維持封裝，不直接存取欄位）
        double gasoline = car1.getGas();                      // 透過 getter 取得汽油量（單位：公升）

        System.out.printf("車號是 %d，汽油量是 %.1f 公升%n",     // 以格式化輸出顯示車輛資訊（%n 為跨平台換行）
                          number, gasoline);                  // 將車號與汽油量帶入格式化字串
    }                                                         // main 方法結束
}                                                             // 類別 Sample3_8_1 結束

final class Car {                                             // 宣告 Car 類別並標示為 final（此範例不需被繼承，保持簡潔）
    private static final int MIN_NUM = 0;                     // 常數：車號允許的最小值（非負）
    private static final double MIN_GAS = 0.0;                // 常數：汽油量下限（不可為負）
    private static final double MAX_GAS = 100.0;              // 常數：油箱上限（示例值，可依實際車款調整）

    private int num;                                          // 私有欄位：車號（封裝避免被外部任意改動）
    private double gas;                                       // 私有欄位：汽油量（單位：公升）

    public Car() {                                            // 無參數建構子：提供合理初始狀態
        this.num = 0;                                         // 初始車號設為 0（視為尚未設定）
        this.gas = 0.0;                                       // 初始汽油量設為 0.0 公升
    }                                                         // 無參數建構子結束

    public Car(int num, double gas) {                         // 完整建構子：可於建立物件時直接指定屬性
        setNumGas(num, gas);                                  // 委派至 setNumGas 進行一次性驗證與設定（避免重複邏輯）
    }                                                         // 完整建構子結束

    public void setNumGas(int n, double g) {                  // 一次設定車號與汽油量的方法（API 一致性較佳）
        if (n < MIN_NUM) {                                    // 檢查：車號不可小於允許下限
            throw new IllegalArgumentException("車號不得為負數"); // 不合法則拋出參數例外，避免不一致狀態
        }                                                     // 車號檢查結束
        if (g < MIN_GAS) {                                    // 檢查：汽油量不可為負數
            throw new IllegalArgumentException("汽油量不得為負數"); // 不合法則拋出參數例外
        }                                                     // 汽油量下限檢查結束
        if (g > MAX_GAS) {                                    // 檢查：汽油量不可超過油箱上限
            throw new IllegalArgumentException(               // 超過上限時拋出參數例外
                "汽油量不可超過油箱上限 " + MAX_GAS + " 公升"); // 提供上限值以利使用者修正
        }                                                     // 汽油量上限檢查結束
        this.num = n;                                         // 通過驗證後設定車號
        this.gas = g;                                         // 通過驗證後設定汽油量
        System.out.printf("將車號設為 %d，汽油量設為 %.1f 公升%n", n, g); // 以格式化輸出回饋設定結果
    }                                                         // setNumGas 方法結束

    public int getNum() {                                     // 取得車號的存取子（不含副作用，便於測試與重用）
        return num;                                           // 回傳目前車號
    }                                                         // getNum 方法結束

    public double getGas() {                                  // 取得汽油量的存取子（不含副作用）
        return gas;                                           // 回傳目前汽油量
    }                                                         // getGas 方法結束

    public void show() {                                      // 顯示目前車輛狀態的方法（可作為除錯或檢視用）
        System.out.printf("車號是 %d%n", this.num);           // 顯示車號（使用 printf 可讀性佳）
        System.out.printf("汽油量是 %.1f 公升%n", this.gas);  // 顯示汽油量（保留 1 位小數並標示單位）
    }                                                         // show 方法結束

    @Override                                                 // 覆寫 Object.toString()：提供友善摘要字串
    public String toString() {                                // 將物件狀態轉為易讀字串
        return String.format("Car{num=%d, gas=%.1f}", num, gas); // 回傳摘要內容：車號與汽油量
    }                                                         // toString 方法結束
}                                                             // 類別 Car 結束
                                                     // 類別 Car 結束
