package crocobob.SISO.Cienderella.Service;

import crocobob.SISO.Cienderella.Domain.Reason;
import crocobob.SISO.Cienderella.Domain.ReasonDTO;
import crocobob.SISO.Cienderella.Repository.Reason.ReasonRepository;
import crocobob.SISO.Exception.DBEntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReasonService {
    private final ReasonRepository reasonRepo;

    public ReasonService(ReasonRepository reasonRepo) {
        this.reasonRepo = reasonRepo;
    }

    public Reason saveReason(ReasonDTO dto) {
        Reason reason = new Reason(dto.getText(), true);

        return reasonRepo.save(reason);
    }

    public Reason getReason(long id) {
        return reasonRepo.findById(id)
                .orElseThrow(() -> new DBEntityNotFoundException("<< No Reason with id : " + id + " found. >>"));
    }

    public List<Reason> getValidReasons(){
        return reasonRepo.findByValidEquals(true);
    }

    public List<Reason> getAllReasons() {
        var reasons = reasonRepo.findAll();
        if(reasons.isEmpty()) throw new DBEntityNotFoundException("<< There is no reason >>");
        else return reasons;
    }

    public Reason patchUpdateReason(long reasonId, Reason newReason) {
        Reason oldReason = getReason(reasonId);

        if(newReason.getText() != null) oldReason.setText(newReason.getText());
        if(newReason.getValid() != null) oldReason.setValid(newReason.getValid());

        return reasonRepo.save(oldReason);
    }

    public void deleteReason(Long id) {
        reasonRepo.delete(reasonRepo.findById(id).orElseThrow(() -> new DBEntityNotFoundException("<< No Reason found >>")));
    }
}
