import java.util.Scanner;

public class Main {
    public static String[] todos = new String[2];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showTodoList() {
        System.out.println("TODO LIST");
        for (int i = 0; i < todos.length; i++) {
            String todo = todos[i];
            if (todos[i] != null) {
                System.out.println((i + 1) + ". " + todo);
            }
        }
    }

    public static void addTodoList(String todo) {
        resizeArrayIfFull();

        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                todos[i] = todo;
                break;
            }
        }
    }

    private static void resizeArrayIfFull() {
        boolean isFull = isArrayFull();

        if (isFull) {
            resizeArrayToTwoTimesBigger();
        }
    }

    private static void resizeArrayToTwoTimesBigger() {
        String[] temp = todos;
        todos = new String[todos.length * 2];
        for (int i = 0; i < temp.length; i++) {
            todos[i] = temp[i];
        }
    }

    private static boolean isArrayFull() {
        boolean isFull = true;
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public static boolean removeTodoList(Integer number) {
        if (isSelectedTodoNotValid(number)) return false;

        for (int i = number - 1; i < todos.length; i++) {
            if (i == (todos.length - 1)) {
                todos[i] = null;
            } else {
                todos[i] = todos[i + 1];
            }
        }
        return true;
    }

    private static boolean isSelectedTodoNotValid(Integer number) {
        if (number <= 0) {
            return true;
        }

        if (number - 1 > todos.length - 1) {
            return true;
        }

        if (todos[number - 1] == null) {
            return true;
        }
        return false;
    }

    public static boolean editTodoList(Integer number, String newTodo) {
        if (isSelectedTodoNotValid(number)) {
            return false;
        }
        todos[number - 1] = newTodo;
        return true;
    }

    public static void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showTodoList();
            System.out.println("MENU : ");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("3. Edit");
            System.out.println("4. Keluar");
            String selectedMenu = input("Pilih");
            switch (selectedMenu) {
                case "1":
                    System.out.println("Menu add todo list");
                    break;
                case "2":
                    System.out.println("Menu remove todo list");
                    break;
                case "3":
                    System.out.println("Menu edit todo list");
                    break;
                case "4":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilih menu dengan benar");
            }
        }
    }
    public static String input(String info) {
        System.out.println(info + " : ");
        String data = scanner.nextLine();
        return data;
    }

    public static void showMenuAddTodoList() {
        System.out.println("MENAMBAH TODO LIST");
        String todo = input("Todo (x jika batal)");
        if (todo.equals("x")) {
            //batal
        } else {
            addTodoList(todo);
        }
    }

    public static void showMenuRemoveTodoList() {
        System.out.println("MENGHAPUS TODO LIST");
        String todoYangDipilih = input("Nomor todo yang dihapus (x jika batal)");
        if (todoYangDipilih.equals("x")) {
            //batal
        } else {
            boolean success = removeTodoList(Integer.valueOf(todoYangDipilih));
            if (!success) {
                System.out.println("Gagal menghapus todo list : " +todoYangDipilih);
            }
        }
    }

    public static void showMenuEditTodoList() {
        System.out.println("MENGEDIT TODO LIST");
        String selectorTodo = input("Masukan nomor todo (x jika batal)");
        if (selectorTodo.equals("x")) {
            return;
        }
        String newTodo = input("Masukan todo yang baru (x jika batal)");
        if (newTodo.equals("x")) {
            return;
        }
        boolean isEditTodoSuccess = editTodoList(Integer.valueOf(selectorTodo), newTodo);
        if (isEditTodoSuccess) {
            System.out.println("Berhasil mengedit todo");
        } else {
            System.out.println("Gagal mengedit todo");
        }
    }
}
