package kr.study.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTest {
	public static void main(String[] args) {
		
		// 현재 날짜
		LocalDate today = LocalDate.now();
		
		System.out.println("오늘 날짜 : " + today);
		
		// 특정 날짜 만들기
		LocalDate date01 = LocalDate.of(2025, 5, 10);
		// yyyy-MM-DD는 패턴 없이 읽을 수 있지만 그 외에는 읽을 패턴을 지정해줘야함
		LocalDate date02 = LocalDate.parse("2025-05-12");
		LocalDate date03 = LocalDate.parse("2025년05월01일", DateTimeFormatter.ofPattern("yyyy년MM월dd일"));
		
		System.out.println(date01);
		System.out.println(date02);
		System.out.println(date03);
		
		// (E) 요일
		// MM 01~12 M 1~12
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일 (E)");
		String nowDate = today.format(formatter);
		System.out.println(nowDate);
		
		System.out.println("=================================");
		System.out.println("연도 : " + today.getYear());
		System.out.println("월(숫자) : " + today.getMonthValue());
		System.out.println("월(영어) : " + today.getMonth());
		System.out.println("일 : " + today.getDayOfMonth()); 
		System.out.println("365일 중 몇 일 : " + today.getDayOfYear()); 
		System.out.println("윤년 여부 : " + today.isLeapYear()); 
		
		System.out.println("=================================");
		// 날짜 변경
		System.out.println("내일 : " + today.plusDays(1));
		System.out.println("어제 : " + today.minusDays(1));
		System.out.println("한달 뒤 : " + today.plusMonths(1));
		System.out.println("한달 전 : " + today.minusMonths(1));
		System.out.println("이번 달 첫 날 : " + today.with(TemporalAdjusters.firstDayOfMonth()));
		System.out.println("이번 달 마지막 날 : " + today.with(TemporalAdjusters.lastDayOfMonth()));
		
		System.out.println("=================================");
		// 날짜 차이 계산
		LocalDate comp1 = LocalDate.of(2025, 6, 10);
		LocalDate comp2 = LocalDate.of(2024, 5, 10);
		
		// 일 수 차이
		// ChronoUnit 시간계산 관련 클래스
		System.out.println("일 차이 : " + ChronoUnit.DAYS.between(comp2, comp1));
		System.out.println("월 차이 : " + ChronoUnit.MONTHS.between(comp2, comp1));
		System.out.println("년 차이 : " + ChronoUnit.YEARS.between(comp2, comp1));
		
		System.out.println("=================================");
		// 날짜 비교
		System.out.println("comp1는 comp2 보다 이후 날짜 인가 ? " + comp1.isAfter(comp2));
		System.out.println("comp1는 comp2 보다 이전 날짜 인가 ? " + comp1.isBefore(comp2));
		System.out.println("comp1는 comp2 는 같은 날 인가 ? " + comp1.equals(comp2));
		
	}
}
