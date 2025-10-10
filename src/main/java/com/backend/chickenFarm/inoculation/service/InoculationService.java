package com.backend.chickenFarm.inoculation.service;

import com.backend.chickenFarm.inoculation.dto.InoculationDTO;
import com.backend.chickenFarm.inoculation.mapper.InoculationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class InoculationService {

    private final InoculationMapper inoculationMapper;

    /**
     * 배치별 닭 목록 조회
     */
    public List<Map<String, Object>> getChickensByBatch(String batchId) {
        try {
            log.info("배치별 닭 목록 조회 - 배치 ID: {}", batchId);
            List<Map<String, Object>> result = inoculationMapper.getChickensByBatch(batchId);
            log.info("조회 결과: {}건", result.size());
            return result;
        } catch (Exception e) {
            log.error("배치별 닭 목록 조회 중 오류", e);
            throw new RuntimeException("배치별 닭 목록 조회 실패: " + e.getMessage(), e);
        }
    }

    /**
     * 농장별 배치 목록 조회
     */
    public List<Map<String, Object>> getBatchesByFarm(int farmNum) {
        try {
            log.info("농장별 배치 목록 조회 - 농장 번호: {}", farmNum);
            List<Map<String, Object>> result = inoculationMapper.getBatchesByFarm(farmNum);
            log.info("조회 결과: {}건", result.size());
            return result;
        } catch (Exception e) {
            log.error("농장별 배치 목록 조회 중 오류", e);
            throw new RuntimeException("배치 목록 조회 실패: " + e.getMessage(), e);
        }
    }

    /**
     * 예방접종 실행 (일괄 처리)
     */
    @Transactional
    public void performInoculation(InoculationDTO request) {
        try {
            log.info("=== 예방접종 실행 시작 ===");
            log.info("닭 IDs: {}", request.getChickenIds());
            log.info("백신 타입: {}", request.getVaccineType());
            log.info("접종 방법: {}", request.getVaccinationMethod());
            log.info("접종자: {}", request.getVaccinatedBy());
            log.info("비고: {}", request.getNotes());

            // 날짜 처리 (LocalDateTime 타입)
            LocalDateTime vaccinationDate = request.getVaccinationDate();
            if (vaccinationDate == null) {
                vaccinationDate = LocalDateTime.now();
            }

            // 일괄 INSERT를 위한 파라미터 준비
            Map<String, Object> params = new HashMap<>();
            params.put("chickenIds", request.getChickenIds());
            params.put("vaccineType", request.getVaccineType());
            params.put("vaccinationDate", vaccinationDate);
            params.put("vaccinationMethod", request.getVaccinationMethod());
            params.put("vaccinatedBy", request.getVaccinatedBy());
            params.put("notes", request.getNotes());

            // 일괄 INSERT 실행
            int insertedCount = inoculationMapper.performInoculation(params);

            log.info("예방접종 완료 - {}마리 처리됨", insertedCount);
            log.info("=== 예방접종 실행 완료 ===");

        } catch (Exception e) {
            log.error("예방접종 처리 중 오류 발생", e);
            throw new RuntimeException("예방접종 처리 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    /**
     * 예방접종 실행 (개별 처리) - 대안 방법
     */
    @Transactional
    public void performInoculationIndividually(InoculationDTO request) {
        try {
            log.info("=== 예방접종 개별 실행 시작 ===");

            // 날짜 처리 (LocalDateTime 타입)
            LocalDateTime vaccinationDate = request.getVaccinationDate();
            if (vaccinationDate == null) {
                vaccinationDate = LocalDateTime.now();
            }

            // 각 닭에 대해 개별 INSERT 실행
            for (Integer chickenId : request.getChickenIds()) {
                Map<String, Object> params = new HashMap<>();
                params.put("chickenId", chickenId);
                params.put("vaccineType", request.getVaccineType());
                params.put("vaccinationDate", vaccinationDate);
                params.put("vaccinationMethod", request.getVaccinationMethod());
                params.put("vaccinatedBy", request.getVaccinatedBy());
                params.put("notes", request.getNotes());

                inoculationMapper.performInoculationSingle(params);
                log.debug("닭 ID {} 접종 완료", chickenId);
            }

            log.info("예방접종 완료 - {}마리 처리됨", request.getChickenIds().size());
            log.info("=== 예방접종 개별 실행 완료 ===");

        } catch (Exception e) {
            log.error("예방접종 개별 처리 중 오류 발생", e);
            throw new RuntimeException("예방접종 처리 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    /**
     * 예방접종 기록 삭제 (미완료 처리)
     */
    @Transactional
    public void deleteInoculation(InoculationDTO request) {
        try {
            log.info("=== 예방접종 기록 삭제 시작 ===");
            log.info("닭 IDs: {}", request.getChickenIds());
            log.info("백신 타입: {}", request.getVaccineType());

            // 삭제를 위한 파라미터 준비
            Map<String, Object> params = new HashMap<>();
            params.put("chickenIds", request.getChickenIds());
            params.put("vaccineType", request.getVaccineType());

            // DELETE 실행
            int deletedCount = inoculationMapper.deleteInoculation(params);

            log.info("예방접종 기록 삭제 완료 - {}건 삭제됨", deletedCount);
            log.info("=== 예방접종 기록 삭제 완료 ===");

        } catch (Exception e) {
            log.error("예방접종 기록 삭제 중 오류 발생", e);
            throw new RuntimeException("예방접종 기록 삭제 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

    /**
     * 특정 닭의 접종 이력 조회
     */
    public List<Map<String, Object>> getInoculationHistory(int chickenId) {
        try {
            log.info("접종 이력 조회 - 닭 ID: {}", chickenId);
            List<Map<String, Object>> result = inoculationMapper.getInoculationHistory(chickenId);
            log.info("조회 결과: {}건", result.size());
            return result;
        } catch (Exception e) {
            log.error("접종 이력 조회 중 오류", e);
            throw new RuntimeException("접종 이력 조회 실패: " + e.getMessage(), e);
        }
    }

    /**
     * 배치별 접종 이력 조회
     */
    public List<Map<String, Object>> getInoculationHistoryByBatch(String batchId) {
        try {
            log.info("배치별 접종 이력 조회 - 배치 ID: {}", batchId);
            List<Map<String, Object>> result = inoculationMapper.getInoculationHistoryByBatch(batchId);
            log.info("조회 결과: {}건", result.size());
            return result;
        } catch (Exception e) {
            log.error("배치별 접종 이력 조회 중 오류", e);
            throw new RuntimeException("배치별 접종 이력 조회 실패: " + e.getMessage(), e);
        }
    }

    /**
     * 배치별 접종 통계 조회
     */
    public Map<String, Object> getInoculationStats(String batchId) {
        try {
            log.info("배치별 접종 통계 조회 - 배치 ID: {}", batchId);
            Map<String, Object> result = inoculationMapper.getInoculationStats(batchId);
            log.info("통계 조회 완료");
            return result;
        } catch (Exception e) {
            log.error("접종 통계 조회 중 오류", e);
            throw new RuntimeException("접종 통계 조회 실패: " + e.getMessage(), e);
        }
    }
}