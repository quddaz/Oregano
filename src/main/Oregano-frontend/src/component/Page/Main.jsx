import React from "react";
import FlexBoxItem from "../UI/FlexBoxItem";
import "../Css/Page.css";
import TextMenu from "../UI/TextMenu";
import LocalStatistical from "../map&graph/localStatistical";

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
      <div className="main-container">
        <TextMenu>종합 통계</TextMenu>
          <FlexBoxItem statistics={statisticsData} />
        <TextMenu>지역별 구인/구직 현황(5월)</TextMenu>
        <LocalStatistical/>
      </div>
  );
}

export default Main;
