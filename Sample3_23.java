public class Sample3_23 {                                                     // 宣告公開類別 Sample3_23（檔名需與類別同名）
    public static void main(String[] args) {                                  // 主程式進入點（JVM 從此開始執行）
        Car car1 = new Car();                                                 // 建立一台 Car 物件（使用無參數建構子，帶預設值與提示）

        car1.show();                                                          // 先顯示初始狀態：車號=0、油量=0.0、車名=「沒有名字」

        int number = 1234;                                                    // 準備要設定的車號（示範用整數）
        double gasoline = 20.5;                                               // 準備要設定的汽油量（單位：公升）
        String carName = "1號車";                                              // 準備要設定的車名（示範用字串）

        car1.setCar(number, gasoline);                                        // 一次設定車號與汽油量（內含參數驗證與回饋）
        car1.setName(carName);                                                // 設定車名（會做空值/空白清理與長度檢查）

        car1.show();                                                          // 再次顯示，確認屬性已正確更新
    }                                                                          // main 方法結束
}                                                                              // 類別 Sample3_23 結束

final class Car {                                                              // 宣告 Car 類別並標示為 final（此範例不需被繼承，保持簡潔）
    private static final double MIN_GAS = 0.0;                                 // 常數：汽油量下限（不可為負）
    private static final double MAX_GAS = 100.0;                               // 常數：汽油量上限（示例值，可依實際車款調整）
    private static final int NAME_MAX_LEN = 30;                                // 常數：車名允許的最大長度（避免過長輸出）

    private int num;                                                           // 私有欄位：車號（封裝，避免外部直接改動）
    private double gas;                                                        // 私有欄位：汽油量（單位：公升）
    private String name;                                                       // 私有欄位：車名（預設為「沒有名字」）

    public Car() {                                                             // 無參數建構子：建立預設狀態並提示
        this.num = 0;                                                          // 預設車號為 0（代表尚未設定）
        this.gas = 0.0;                                                        // 預設汽油量為 0.0 公升
        this.name = "沒有名字";                                                     // 預設車名（符合題目原意）
        System.out.println("生產了車子");                                          // 與題目一致：建立時輸出提示訊息
    }                                                                           // 無參數建構子結束

    public Car(int n, double g, String nm) {                                   // 參數建構子：可於建立時直接指定所有屬性
        setCar(n, g);                                                           // 透過共用邏輯驗證並設定車號與油量（避免重複程式碼）
        setName(nm);                                                            // 設定車名（內含清理/驗證）
    }                                                                           // 參數建構子結束

    public void setCar(int n, double g) {                                      // 同時設定車號與汽油量的方法（先驗證、後更新）
        if (n < 0) {                                                            // 驗證：車號不可為負數
            throw new IllegalArgumentException("車號不得為負數");                 // 不合法則拋出參數例外（避免物件不一致）
        }                                                                        // 車號驗證結束
        if (g < MIN_GAS || g > MAX_GAS) {                                       // 驗證：汽油量需位於 [MIN_GAS, MAX_GAS] 範圍
            throw new IllegalArgumentException(                                 // 不合法則拋出參數例外
                String.format("汽油量需介於 %.1f 與 %.1f 公升之間", MIN_GAS, MAX_GAS)); // 提供合法區間以利修正
        }                                                                        // 汽油量驗證結束
        this.num = n;                                                            // 通過驗證後更新車號
        this.gas = g;                                                            // 通過驗證後更新汽油量
        System.out.printf("將車號設為 %d，汽油量設為 %.1f 公升%n", num, gas);        // 以格式化輸出回饋設定結果（%n 跨平台換行）
    }                                                                           // setCar 方法結束

    public void setName(String nm) {                                           // 設定車名的方法（含清理與長度檢查）
        String cleaned = (nm == null) ? "" : nm.trim();                         // 將 null 轉為空字串並去除前後空白
        if (cleaned.isEmpty()) {                                                // 若為空字串（或原本是空白）
            cleaned = "沒有名字";                                                  // 採用預設名稱以避免空名
        }                                                                        // 空名處理結束
        if (cleaned.length() > NAME_MAX_LEN) {                                  // 若超過長度上限
            cleaned = cleaned.substring(0, NAME_MAX_LEN);                       // 進行截斷避免過長（亦可選擇拋例外）
        }                                                                        // 長度限制處理結束
        this.name = cleaned;                                                     // 指派清理後的名稱
        System.out.printf("將車名設為 %s%n", this.name);                          // 以格式化輸出回饋設定結果
    }                                                                           // setName 方法結束

    public int getNum() {                                                      // Getter：取得車號（維持封裝）
        return num;                                                             // 回傳目前車號
    }                                                                           // getNum 方法結束

    public double getGas() {                                                   // Getter：取得汽油量（維持封裝）
        return gas;                                                             // 回傳目前汽油量
    }                                                                           // getGas 方法結束

    public String getName() {                                                  // Getter：取得車名（維持封裝）
        return name;                                                            // 回傳目前車名
    }                                                                           // getName 方法結束

    public void show() {                                                       // 成員方法：顯示目前車輛狀態
        System.out.printf("車號是 %d%n", this.num);                             // 顯示車號（printf 可讀性佳）
        System.out.printf("汽油量是 %.1f 公升%n", this.gas);                    // 顯示汽油量（保留 1 位小數與單位）
        System.out.printf("車名是 %s%n", this.name);                            // 顯示車名
    }                                                                           // show 方法結束

    @Override                                                                   // 覆寫 Object.toString()：提供友善摘要字串
    public String toString() {                                                  // 將物件狀態轉為易讀字串（利於除錯/記錄）
        return String.format("Car{num=%d, gas=%.1f, name=%s}", num, gas, name); // 回傳車號/油量/車名的摘要資訊
    }                                                                           // toString 方法結束
}                                                                               // 類別 Car 結束

