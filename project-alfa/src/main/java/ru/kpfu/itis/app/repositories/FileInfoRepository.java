package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.FileInfo;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface FileInfoRepository extends JpaRepository<FileInfo, Long>{
}
