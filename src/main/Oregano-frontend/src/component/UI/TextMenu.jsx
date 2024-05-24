import React from "react";
import styled from "styled-components";


const ContentText = styled.p`
    font-size: 30px;
    line-height: 32px;
    white-space: pre-wrap;
    font-weight: bold;
    text-align: left;
`;


function TextMenu({ children }) {
    return (
        <ContentText>{children}</ContentText>
    );
}

export default TextMenu;