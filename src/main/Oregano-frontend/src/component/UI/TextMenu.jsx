import React from "react";
import styled from "styled-components";

//Text 메뉴 전용 UI 컴포넌트 

const ContentText = styled.p`
    font-size: 20px;
    line-height: 32px;
    white-space: pre-wrap;
    font-weight: bold;
`;


function TextMenu({ children }) {
    return (
        <ContentText>{children}</ContentText>
    );
}

export default TextMenu;