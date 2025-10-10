package com.backend.chickenFarm.inoculation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface InoculationMapper {

    /**
     * 배치별 닭 목록 조회 (백신 접종 여부 포함)
     * @param batchId 배치 ID
     * @return 닭 목록
     */
    List<Map<String, Object>> getChickensByBatch(@Param("batchId") String batchId);

    /**
     * 농장별 배치 목록 조회
     * @param farmNum 농장 번호
     * @return 배치 목록
     */
    List<Map<String, Object>> getBatchesByFarm(@Param("farmNum") int farmNum);

    /**
     * 예방접종 일괄 실행
     * @param params 접종 정보 (chickenIds, vaccineType, vaccinationDate 등)
     * @return 삽입된 행 수
     */
    int performInoculation(Map<String, Object> params);

    /**
     * 예방접종 개별 실행
     * @param params 접종 정보 (chickenId, vaccineType, vaccinationDate 등)
     * @return 삽입된 행 수
     */
    int performInoculationSingle(Map<String, Object> params);

    /**
     * 예방접종 기록 삭제 (미완료 처리)
     * @param params 삭제 정보 (chickenIds, vaccineType)
     * @return 삭제된 행 수
     */
    int deleteInoculation(Map<String, Object> params);

    /**
     * 특정 닭의 접종 이력 조회
     * @param chickenId 닭 ID
     * @return 접종 이력 목록
     */
    List<Map<String, Object>> getInoculationHistory(@Param("chickenId") int chickenId);

    /**
     * 배치별 접종 이력 조회
     * @param batchId 배치 ID
     * @return 접종 이력 목록
     */
    List<Map<String, Object>> getInoculationHistoryByBatch(@Param("batchId") String batchId);

    /**
     * 배치별 접종 통계 조회
     * @param batchId 배치 ID
     * @return 접종 통계 (백신별 완료 수)
     */
    Map<String, Object> getInoculationStats(@Param("batchId") String batchId);
}