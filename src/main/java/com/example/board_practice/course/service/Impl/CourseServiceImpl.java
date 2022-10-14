package com.example.board_practice.course.service.Impl;

import com.example.board_practice.course.dto.CourseDto;
import com.example.board_practice.course.dto.InputCourseDto;
import com.example.board_practice.course.entity.Course;
import com.example.board_practice.course.mapper.CourseMapper;
import com.example.board_practice.course.model.CourseParam;
import com.example.board_practice.course.repository.CourseRepository;
import com.example.board_practice.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    private LocalDate getLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(InputCourseDto courseDto) {
        LocalDate saleEndAt = getLocalDate(courseDto.getSaleEndDt());
        Course course = Course.builder()
                .categoryId(courseDto.getCategoryId())
                .subject(courseDto.getSubject())
                .keyword(courseDto.getKeyword())
                .summary(courseDto.getSummary())
                .contents(courseDto.getContents())
                .price(courseDto.getPrice())
                .salePrice(courseDto.getSalePrice())
                .saleEndDt(saleEndAt)
                .registeredAt(LocalDateTime.now())
                .filename(courseDto.getFilename())
                .urlFilename(courseDto.getUrlFilename())
                .build();
        courseRepository.save(course);
    }

    @Override
    public boolean set(InputCourseDto courseDto) {
        LocalDate saleEndAt = getLocalDate(courseDto.getSaleEndDt());
        Optional<Course> optionalCourse = courseRepository.findById(courseDto.getId());
        if (!optionalCourse.isPresent()) { // 수정할 데이터가 없는 경우
            return false;
        }
        Course course = optionalCourse.get();
        course.setCategoryId(courseDto.getCategoryId())
                .setSubject(courseDto.getSubject())
                .setKeyword(courseDto.getKeyword())
                .setSummary(courseDto.getSummary())
                .setContents(courseDto.getContents())
                .setPrice(courseDto.getPrice())
                .setSalePrice(courseDto.getSalePrice())
                .setPrice(courseDto.getPrice())
                .setSaleEndDt(saleEndAt)
                .setUpdatedAt(LocalDateTime.now())
                .setFilename(courseDto.getFilename())
                .setUrlFilename(courseDto.getUrlFilename());
        courseRepository.save(course);
        return true;
    }

    @Override
    public List<CourseDto> list(CourseParam param) {
        long totalCount = courseMapper.selectListCount(param);

        List<CourseDto> list = courseMapper.selectList(param);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (CourseDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - param.getPageStart() - i);
                i++;
            }
        }
        return list;
    }
    @Override
    public CourseDto getById(long id) {
        return courseRepository.findById(id).map(CourseDto::fromEntity).orElse(null);
    }


    @Override
    public void delete(String idList) {
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (id > 0) {
                    courseRepository.deleteById(id);
                }
            }
        }
    }
}
