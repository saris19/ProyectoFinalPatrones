package com.example.demo.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class FileWatcherService {

    private final JsonLoader jsonLoader;  // Usamos tu JsonLoader actual

    private final ExecutorService executor = Executors.newSingleThreadExecutor(); // Hilo separado

    @PostConstruct
    public void watchFile() {
        Path path = Paths.get("../detections/");
        String filename = "detections_precision.json";

        executor.submit(() -> {
            try {
                WatchService watchService = FileSystems.getDefault().newWatchService();
                path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

                System.out.println("[👀] Vigilando cambios en: " + path.toAbsolutePath());

                while (true) {
                    WatchKey key = watchService.take(); // Espera a que algo pase
                    for (WatchEvent<?> event : key.pollEvents()) {
                        Path changed = (Path) event.context();

                        if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY &&
                            changed.toString().equals(filename)) {

                            System.out.println("[📂] JSON actualizado, importando...");
                            String filePath = path.resolve(filename).toString();
                            jsonLoader.loadJsonAndSaveToDb(filePath);
                            System.out.println("[✅] Importación automática lista");
                        }
                    }
                    key.reset();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

