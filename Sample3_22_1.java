public class Sample3_22_1 {                                                // 宣告公開類別 Sample3_22（檔名需與類別同名）
    public static void main(String[] args) {                              // 主程式進入點（JVM 從這裡開始執行）
        System.out.println("宣告 car1");                                  // 提示：即將宣告並建立第一台車 car1
        Car car1 = new Car();                                             // 以無參數建構子建立 car1（會印出「生產了車子」）
        car1.setCar(1234, 20.5);                                          // 設定 car1 的車號與汽油量（內含基本驗證與回饋）

        System.out.println("宣告 car2（尚未指向任何 Car 物件）");             // 提示：僅宣告 car2 變數，尚未賦值
        Car car2;                                                         // 宣告參考型別變數 car2（目前為未初始化狀態）

        System.out.println("將 car1 指定給 car2（兩者指向同一個物件）");        // 提示：car2 = car1 會造成別名（aliasing）
        car2 = car1;                                                      // 參考賦值：car2 與 car1 指向同一個 Car 物件

        System.out.println("car1 的：");                                   // 標示接下來輸出的是 car1 的內容
        car1.show();                                                      // 顯示 car1 的車號與汽油量（實際讀同一物件狀態）
        System.out.println("car2 的：");                                   // 標示接下來輸出的是 car2 的內容
        car2.show();                                                      // 顯示 car2 的車號與汽油量（與 car1 相同，因為同一物件）

        System.out.println("改變 car1 的相關資料（會影響 car2，因為同物件）");       // 提示：修改 car1 也會影響 car2 觀察到的值
        car1.setCar(2345, 30.5);                                          // 再次設定同一物件的車號與汽油量

        System.out.println("car1 的：");                                   // 再次輸出 car1 的內容
        car1.show();                                                      // 確認 car1 已更新
        System.out.println("car2 的：");                                   // 再次輸出 car2 的內容
        car2.show();                                                      // 可見 car2 也隨之改變（因為兩者是同一物件）

        // ===== 進階補充：如果想要「複製」而不是「共用同一物件」 =====              // 說明：避免別名問題可做深拷貝
        System.out.println("（進階）建立 car3 為 car1 的複本（不同物件）");             // 提示：即將建立 car1 的拷貝 car3
        Car car3 = new Car(car1);                                         // 使用拷貝建構子建立 car3（內容相同但不同物件）
        car1.setCar(7777, 50.0);                                          // 修改 car1，不應影響 car3
        System.out.println("修改 car1 後：car1 與 car3 各自的狀態");              // 提示：比較 car1 與 car3
        System.out.println("car1 的：");                                   // 標示 car1
        car1.show();                                                      // 顯示 car1 目前狀態
        System.out.println("car3 的：");                                   // 標示 car3
        car3.show();                                                      // 顯示 car3 目前狀態（保持拷貝時的值，未受 car1 影響）
    }                                                                      // main 方法結束
}                                                                          // 類別 Sample3_22 結束

final class Car {                                                          // 宣告 Car 類別並標示為 final（本範例不需被繼承）
    private static final double MIN_GAS = 0.0;                             // 常數：汽油量下限（不可為負）
    private static final double MAX_GAS = 100.0;                           // 常數：汽油量上限（示例值，可依需求調整）

    private int num;                                                       // 私有欄位：車號（封裝避免外部任意改動）
    private double gas;                                                    // 私有欄位：汽油量（單位：公升）

    public Car() {                                                         // 無參數建構子：建立預設狀態的車
        this.num = 0;                                                      // 預設車號為 0（代表尚未設定）
        this.gas = 0.0;                                                    // 預設汽油量為 0.0 公升
        System.out.println("生產了車子");                                    // 與題目一致：建立時輸出提示訊息
    }                                                                      // 無參數建構子結束

    public Car(int n, double g) {                                          // 參數建構子：直接以車號與油量建立車
        setCar(n, g);                                                      // 透過共用邏輯 setCar 進行驗證與設定（避免重複程式碼）
    }                                                                      // 參數建構子結束

    public Car(Car other) {                                                // 拷貝建構子：由另一台車建立相同狀態的新物件
        this.num = other.num;                                              // 複製車號（基本型別可直接複製）
        this.gas = other.gas;                                              // 複製汽油量（基本型別可直接複製）
        System.out.println("生產了車子的拷貝");                                // 額外提示：這是一個複製品
    }                                                                      // 拷貝建構子結束

    public void setCar(int n, double g) {                                  // 同時設定車號與汽油量的方法（先驗證、後更新）
        if (n < 0) {                                                       // 驗證：車號不可為負數
            throw new IllegalArgumentException("車號不得為負數");            // 不合法則拋出參數例外
        }                                                                  // 車號驗證結束
        if (g < MIN_GAS || g > MAX_GAS) {                                  // 驗證：汽油量需位於 [0, 100] 範圍
            throw new IllegalArgumentException(                            // 不合法則拋出參數例外
                String.format("汽油量需介於 %.1f 與 %.1f 公升之間", MIN_GAS, MAX_GAS)); // 友善錯誤訊息
        }                                                                  // 汽油量驗證結束
        this.num = n;                                                      // 通過驗證後設定車號
        this.gas = g;                                                      // 通過驗證後設定汽油量
        System.out.printf("將車號設為 %d，汽油量設為 %.1f 公升%n", num, gas); // 回饋設定結果（%n 跨平台換行）
    }                                                                      // setCar 方法結束

    public void show() {                                                   // 顯示目前車輛狀態的方法
        System.out.printf("車號是 %d%n", this.num);                        // 以格式化輸出顯示車號（可讀性佳）
        System.out.printf("汽油量是 %.1f 公升%n", this.gas);               // 以格式化輸出顯示汽油量（保留 1 位小數）
    }                                                                      // show 方法結束

    @Override                                                              // 覆寫 Object.toString() 以提供友善摘要
    public String toString() {                                             // 將物件狀態轉為易讀字串
        return String.format("Car{num=%d, gas=%.1f}", num, gas);           // 回傳摘要內容：車號與汽油量
    }                                                                      // toString 方法結束
}                                                                          // 類別 Car 結束

