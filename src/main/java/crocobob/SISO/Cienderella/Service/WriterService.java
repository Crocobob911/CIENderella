package crocobob.SISO.Cienderella.Service;

import crocobob.SISO.Cienderella.Domain.Writer;
import crocobob.SISO.Cienderella.Domain.WriterDTO;
import crocobob.SISO.Cienderella.Repository.Writer.WriterRepository;
import crocobob.SISO.Exception.DBEntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterService {

    private final WriterRepository writerRepo;

    public WriterService(WriterRepository writerRepo) {
        this.writerRepo = writerRepo;
    }

    public Writer saveWriter(WriterDTO dto) {
        Writer writer = new Writer(dto.getText(), true);

        return writerRepo.save(writer);
    }

    public Writer getWriter(long id) {
        return writerRepo.findById(id)
                .orElseThrow(() -> new DBEntityNotFoundException("<< No Writer with id : " + id + " found. >>"));
    }

    public List<Writer> getValidWriters(){
        return writerRepo.findByValidEquals(true);
    }

    public List<Writer> getAllWriters() {
        var writers = writerRepo.findAll();
        if(writers.isEmpty()) throw new DBEntityNotFoundException("<< There is no writer >>");
        else return writers;
    }

    public Writer patchUpdateWriter(long writerId, Writer newWriter) {
        Writer oldWriter = getWriter(writerId);

        if(newWriter.getText() != null) oldWriter.setText(newWriter.getText());
        if(newWriter.getValid() != null) oldWriter.setValid(newWriter.getValid());

        return writerRepo.save(oldWriter);
    }

    public void deleteWriter(Long id) {
        writerRepo.delete(writerRepo.findById(id).orElseThrow(() -> new DBEntityNotFoundException("<< No Writer found >>")));
    }
}
