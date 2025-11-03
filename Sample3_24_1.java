public class Sample3_24_1 {                                      // 宣告公開類別 Sample3_24_1（檔名需與類別同名）
    public static void main(String[] args) {                      // 主程式進入點（JVM 從這裡開始執行）
        Car[] cars = new Car[3];                                  // 建立可容納 3 台車的陣列；此時每個元素預設為 null
                                                                  // （僅配置陣列本身，尚未建立各 Car 物件）

        for (int i = 0; i < cars.length; i++) {                   // 迴圈走訪每一個陣列索引（0、1、2）
            cars[i] = new Car();                                  // 在第 i 個位置建立一台新的 Car 物件（呼叫無參數建構子）
        }                                                         // 物件建立完成

        cars[0].setCar(1234, 20.5);                               // 設定第 1 台車的車號與汽油量
        cars[1].setCar(2345, 30.5);                               // 設定第 2 台車的車號與汽油量
        cars[2].setCar(3456, 40.5);                               // 設定第 3 台車的車號與汽油量

        for (int i = 0; i < cars.length; i++) {                   // 再次走訪陣列，逐一輸出每台車的狀態
            System.out.printf("第 %d 台車：%n", i + 1);            // 先輸出車的順序編號（人類習慣從 1 開始）
            cars[i].show();                                       // 呼叫第 i 台車的 show() 顯示車號與汽油量
        }                                                         // 顯示完成
    }                                                             // main 方法結束
}                                                                 // 類別 Sample3_24_1 結束

final class Car {                                                 // 宣告 Car 類別並標示為 final（本範例不需被繼承）
    private static final double MIN_GAS = 0.0;                    // 常數：汽油量下限（不可為負）
    private static final double MAX_GAS = 100.0;                  // 常數：汽油量上限（示例值，可依實際車款調整）

    private int num;                                              // 私有欄位：車號（封裝，避免被外部任意修改）
    private double gas;                                           // 私有欄位：汽油量（單位：公升）

    public Car() {                                                // 無參數建構子：提供合理初始狀態並提示
        this.num = 0;                                             // 預設車號為 0（代表尚未設定）
        this.gas = 0.0;                                           // 預設汽油量為 0.0 公升
        System.out.println("生產了車子");                          // 與原題一致：建立物件時輸出提示訊息
    }                                                             // 無參數建構子結束

    public Car(int n, double g) {                                 // 參數建構子：建立時直接指定車號與汽油量
        setCar(n, g);                                             // 重用 setCar 的驗證與指派邏輯（避免重複程式碼）
        System.out.printf("生產了車號為 %d，汽油量為 %.1f 的車子%n", n, g); // 額外輸出建立完成的詳細資訊
    }                                                             // 參數建構子結束

    public void setCar(int n, double g) {                         // 同時設定車號與汽油量的方法（先驗證、後更新）
        if (n < 0) {                                              // 驗證：車號不可為負數
            throw new IllegalArgumentException("車號不得為負數");   // 不合法則拋出參數例外（避免物件進入不一致狀態）
        }                                                         // 車號驗證結束
        if (g < MIN_GAS || g > MAX_GAS) {                         // 驗證：汽油量需位於 [MIN_GAS, MAX_GAS] 範圍
            throw new IllegalArgumentException(                   // 超出範圍則拋出參數例外
                String.format("汽油量需介於 %.1f 與 %.1f 公升之間", MIN_GAS, MAX_GAS)); // 清楚告知合法區間
        }                                                         // 汽油量驗證結束
        this.num = n;                                             // 通過驗證後：更新車號
        this.gas = g;                                             // 通過驗證後：更新汽油量
        System.out.printf("將車號設為 %d，汽油量設為 %.1f 公升%n", num, gas); // 回饋設定結果（%n 跨平台換行）
    }                                                             // setCar 方法結束

    public void show() {                                          // 物件方法：顯示目前車輛狀態
        System.out.printf("車號是 %d%n", this.num);               // 顯示車號（使用 printf 提升可讀性）
        System.out.printf("汽油量是 %.1f 公升%n", this.gas);      // 顯示汽油量（保留 1 位小數並標示單位）
    }                                                             // show 方法結束

    public int getNum() {                                         // Getter：取得車號（維持封裝）
        return num;                                               // 回傳目前車號
    }                                                             // getNum 方法結束

    public double getGas() {                                      // Getter：取得汽油量（維持封裝）
        return gas;                                               // 回傳目前汽油量
    }                                                             // getGas 方法結束

    @Override                                                     // 覆寫 Object.toString() 以提供友善摘要
    public String toString() {                                    // 將物件狀態轉為易讀字串
        return String.format("Car{num=%d, gas=%.1f}", num, gas);  // 回傳摘要內容：車號與汽油量
    }                                                             // toString 方法結束
}                                                                 // 類別 Car 結束

