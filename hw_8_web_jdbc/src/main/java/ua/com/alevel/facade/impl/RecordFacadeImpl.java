package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.RecordFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Record;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.RecordService;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.RecordRequestDto;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.RecordResponseDto;

import java.util.List;

@Service
public class RecordFacadeImpl implements RecordFacade {

    private final RecordService recordService;
    private final StudentService studentService;
    private final GroupService groupService;

    public RecordFacadeImpl(RecordService recordService, StudentService studentService, GroupService groupService) {
        this.recordService = recordService;
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @Override
    public void create(RecordRequestDto recordRequestDto) {
        Record record = new Record();
        record.setGroup(groupService.findById(recordRequestDto.getGroupId()));
        record.setStudent(studentService.findById(recordRequestDto.getStudentId()));
        recordService.create(record);
    }

    @Override
    public void update(RecordRequestDto recordRequestDto, Long id) {
        Record record = new Record();
        record.setId(id);
        record.setGroup(groupService.findById(recordRequestDto.getGroupId()));
        record.setStudent(studentService.findById(recordRequestDto.getStudentId()));
        recordService.update(record);
    }

    @Override
    public void delete(Long id) {
        recordService.delete(id);
    }

    @Override
    public RecordResponseDto findById(Long id) {
        return new RecordResponseDto(recordService.findById(id));
    }

    @Override
    public PageData<RecordResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        if (sortData.getSort().equals("created")) {
            sortData.setSort("record." + sortData.getSort());
        }
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);
        DataTableResponse<Record> all = recordService.findAll(dataTableRequest);
        List<RecordResponseDto> list = all.getItems().stream().map(RecordResponseDto::new).toList();
        PageData<RecordResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(list, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }
}
