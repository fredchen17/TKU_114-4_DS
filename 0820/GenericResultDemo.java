public class GenericResultDemo {
    public static void main(String[] args) {
        System.out.println("=== 測試 Result<String> ===");
        Result<String> successString = Result.success("Operation completed successfully!");
        Result<String> failString = Result.failure("User not found");

        if (successString.isSuccess()) {
            String strData = successString.getData();
            System.out.println("成功訊息: " + strData);
        }

        if (!failString.isSuccess()) {
            System.out.println("失敗原因: " + failString.getMessage());
            System.out.println("失敗資料 (預期 null): " + failString.getData());
        }

        System.out.println("\n=== 測試 Result<Integer> ===");
        Result<Integer> successInt = Result.success(200);
        Result<Integer> failInt = Result.failure("Invalid status code");

        if (successInt.isSuccess()) {
            Integer intData = successInt.getData();
            System.out.println("成功狀態碼: " + intData);
        }

        if (!failInt.isSuccess()) {
            System.out.println("失敗原因: " + failInt.getMessage());
            System.out.println("失敗資料 (預期 null): " + failInt.getData());
        }
    }
}

class Result<T> {
    private boolean success;
    private String message;
    private T data;

    private Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, "Success", data);
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}