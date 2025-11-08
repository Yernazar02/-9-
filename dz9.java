import java.util.*;

class TV {
    public void turnOn() { System.out.println("Телевизор включен."); }
    public void turnOff() { System.out.println("Телевизор выключен."); }
    public void setChannel(int channel) { System.out.println("Выбран канал: " + channel); }
}

class AudioSystem {
    public void turnOn() { System.out.println("Аудиосистема включена."); }
    public void turnOff() { System.out.println("Аудиосистема выключена."); }
    public void setVolume(int level) { System.out.println("Громкость установлена на уровень: " + level); }
}

class DVDPlayer {
    public void play() { System.out.println("Воспроизведение DVD начато."); }
    public void pause() { System.out.println("DVD на паузе."); }
    public void stop() { System.out.println("Воспроизведение DVD остановлено."); }
}

class GameConsole {
    public void turnOn() { System.out.println("Игровая консоль включена."); }
    public void startGame(String gameName) { System.out.println("Запуск игры: " + gameName); }
}

class HomeTheaterFacade {
    private TV tv;
    private AudioSystem audio;
    private DVDPlayer dvd;
    private GameConsole console;

    public HomeTheaterFacade(TV tv, AudioSystem audio, DVDPlayer dvd, GameConsole console) {
        this.tv = tv;
        this.audio = audio;
        this.dvd = dvd;
        this.console = console;
    }

    public void watchMovie() {
        System.out.println("\n--- Сценарий: Просмотр фильма ---");
        tv.turnOn();
        tv.setChannel(5);
        audio.turnOn();
        audio.setVolume(15);
        dvd.play();
    }

    public void listenMusic() {
        System.out.println("\n--- Сценарий: Прослушивание музыки ---");
        tv.turnOn();
        audio.turnOn();
        audio.setVolume(10);
        System.out.println("Аудиовход подключен к телевизору.");
    }

    public void playGame(String gameName) {
        System.out.println("\n--- Сценарий: Играть в игру ---");
        tv.turnOn();
        console.turnOn();
        audio.turnOn();
        audio.setVolume(12);
        console.startGame(gameName);
    }

    public void setVolume(int level) {
        System.out.println("\n--- Изменение громкости через фасад ---");
        audio.setVolume(level);
    }

    public void turnOffAll() {
        System.out.println("\n--- Отключение всей системы ---");
        dvd.stop();
        audio.turnOff();
        tv.turnOff();
        System.out.println("Система выключена.");
    }
}

abstract class FileSystemComponent {
    protected String name;
    public FileSystemComponent(String name) { this.name = name; }
    public abstract void display(String indent);
    public abstract int getSize();
}

class File extends FileSystemComponent {
    private int size;
    public File(String name, int size) { super(name); this.size = size; }
    public void display(String indent) { System.out.println(indent + "- Файл: " + name + " (" + size + " KB)"); }
    public int getSize() { return size; }
}

class Directory extends FileSystemComponent {
    private List<FileSystemComponent> components = new ArrayList<>();
    public Directory(String name) { super(name); }

    public void add(FileSystemComponent component) {
        if (!components.contains(component)) components.add(component);
        else System.out.println("Компонент '" + component.name + "' уже существует в '" + name + "'.");
    }

    public void remove(FileSystemComponent component) {
        if (components.contains(component)) components.remove(component);
        else System.out.println("Компонент '" + component.name + "' не найден в '" + name + "'.");
    }

    public void display(String indent) {
        System.out.println(indent + "[Папка]: " + name);
        for (FileSystemComponent component : components) component.display(indent + "   ");
    }

    public int getSize() {
        int total = 0;
        for (FileSystemComponent component : components) total += component.getSize();
        return total;
    }
}

public class dz9 {
    public static void main(String[] args) {
        TV tv = new TV();
        AudioSystem audio = new AudioSystem();
        DVDPlayer dvd = new DVDPlayer();
        GameConsole console = new GameConsole();
        HomeTheaterFacade home = new HomeTheaterFacade(tv, audio, dvd, console);
        home.watchMovie();
        home.setVolume(18);
        home.playGame("FIFA 25");
        home.listenMusic();
        home.turnOffAll();

        System.out.println("\n===============================");
        System.out.println("=== ФАЙЛОВАЯ СИСТЕМА ===");
        System.out.println("===============================");

        File f1 = new File("Доклад.docx", 120);
        File f2 = new File("Фото.jpg", 800);
        File f3 = new File("Музыка.mp3", 5000);
        Directory d1 = new Directory("Документы");
        Directory d2 = new Directory("Мультимедиа");
        Directory root = new Directory("Мой компьютер");
        d1.add(f1);
        d2.add(f2);
        d2.add(f3);
        root.add(d1);
        root.add(d2);
        root.display("");
        System.out.println("\nОбщий размер: " + root.getSize() + " KB");
    }
}
