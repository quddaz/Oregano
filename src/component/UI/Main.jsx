import React from "react";
import styled from "styled-components";
import FlexBoxItem from "./FlexBoxItem";
import KorMap from "../map/KorMap";
import PopulationTable from "./PopulationTable";
const Container = styled.div`
  padding: 20px;
  background-color: #f9f9f9;
  padding: 10%;
`;
const Container2 = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 20px;
  background-color: #f0f0f0;
`;
const Header = styled.h2`
  text-align: left;
  font-size: 24px;
  margin-bottom: 20px;
`;

//Spring을 통한 구현 필요
const statisticsData = [
  {
    title: "전체 구인 건수",
    number: "3,042,132명",
    percentage: "+ 28%",
    isPositive: true
  },
  {
    title: "전체 구직 건수",
    number: "2,752,132명",
    percentage: "- 28%",
    isPositive: false
  },
  {
    title: "남은 구인 건수",
    number: "245,123명",
    percentage: "- 28%",
    isPositive: false
  }
];

function Main() {
  return (
    <Container>
      <Header>종합 통계</Header>
      <FlexBoxItem statistics={statisticsData} />
      <Header>지역별 구인/구직 현황(5월)</Header>
      <Container2>
        <KorMap/>
        <PopulationTable/>
      </Container2>

    </Container>
  );
}

export default Main;
