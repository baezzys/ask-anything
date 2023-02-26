import { useState } from "react";
import { css } from "@emotion/react";

export type MenuQuery = "answered" | "new" | "rejected";

type Menu = {
  name: string;
  query: MenuQuery;
};

type TabProps = {
  currentTab: MenuQuery;
  setCurrentTab: React.Dispatch<React.SetStateAction<MenuQuery>>;
};

export const Tab = ({ currentTab, setCurrentTab }: TabProps) => {
  const menuArr: Menu[] = [
    { name: "답변한 질문", query: "answered" },
    { name: "새로운 질문", query: "new" },
    { name: "거절한 질문", query: "rejected" },
  ];

  const selectMenuHandler = (query: MenuQuery) => {
    setCurrentTab(query);
  };

  return (
    <div css={tabMenu}>
      {menuArr.map(({ name, query }) => (
        <li key={query}>
          <button
            className={query === currentTab ? "submenu focused" : "submenu"}
            onClick={() => selectMenuHandler(query)}
          >
            {name}
          </button>
        </li>
      ))}
    </div>
  );
};

const tabMenu = css`
  font-weight: bold;
  display: flex;
  flex-direction: row;
  align-items: center;
  list-style: none;

  li {
    width: 100%;
  }

  .submenu {
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid #f5f5f5;
    border-bottom: 1px solid #000;
    color: #a6a6a6;
    width: 100%;
    padding: 18px;
    font-size: 15px;
    border-radius: 25px 25px 0px 0px;
  }

  .focused {
    border: 1px solid #000;
    border-bottom: none;
    color: rgb(21, 20, 20);
    font-weight: 700;
  }

  & div.desc {
    text-align: center;
  }
`;
