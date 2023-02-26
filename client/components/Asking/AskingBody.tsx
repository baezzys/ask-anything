import { css } from "@emotion/react";
import { MenuQuery, Tab } from "components/Asking/atom/Tap";
import { useState } from "react";
import { Asks } from "types/asking";
import Ask from "./atom/Ask";

function AskingBody({ total_count, asks }: Asks) {
  const [currentTab, setCurrentTab] = useState<MenuQuery>("answered");

  return (
    <div>
      <Tab currentTab={currentTab} setCurrentTab={setCurrentTab} />

      <div css={questionCount}>
        <span>
          질문 <strong>{total_count}</strong>
        </span>
      </div>

      <ul>
        {asks.map((ask) => (
          <li css={question}>
            <Ask {...ask} />
          </li>
        ))}
      </ul>
    </div>
  );
}
const question = css`
  border: 1px solid #f0f0f0;
  border-right: none;
  border-left: none;
`;

const questionCount = css`
  padding: 14px 25px;
  span {
    font-weight: 700;
  }
  strong {
    color: #ff008a;
    font-weight: 500;
  }
`;

export default AskingBody;
