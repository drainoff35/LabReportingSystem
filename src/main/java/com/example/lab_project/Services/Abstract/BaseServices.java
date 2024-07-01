package com.example.lab_project.Services.Abstract;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface BaseServices<T,ID> {
    public List<T> GetAll();
    public Optional<T> GetById(ID id);
    public T Create(T t);
    public T Update(ID id, T t);
    public T Delete(ID id);
}
