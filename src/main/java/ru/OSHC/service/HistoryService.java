package ru.OSHC.service;

import org.springframework.stereotype.Service;
import ru.OSHC.dao.HistoryDAO;
import ru.OSHC.entity.History;

@Service
public class HistoryService extends BaseService<History> implements HistoryDAO {

}
