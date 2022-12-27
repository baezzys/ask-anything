import { css } from "@emotion/react";
import A11yHidden from "components/common/A11yHidden";
import { useState } from "react";
import { MdKeyboardArrowDown } from "react-icons/md";
import { FaUserAlt, FaSignOutAlt } from "react-icons/fa";
import { BsFillGearFill } from "react-icons/bs";
import pxToRem from "utils/style/pxToRem";
import useClickEvent from "hook/useClickEvent";
import useKeyboardEvent from "hook/useKeyboardEvent";

const UserButton = () => {
  const [isOpened, setIsOpened] = useState(false);

  const close = (e: Event) => {
    const target = e.target as Element;
    if (!target || target.closest("#userButton")) return;

    setIsOpened(false);
  };

  useClickEvent(close);
  useKeyboardEvent("Escape", () => setIsOpened(false));

  return (
    <div id="userButton" css={container}>
      <button
        css={button}
        type="button"
        onClick={() => setIsOpened((prev) => !prev)}
      >
        <A11yHidden>사용자 정보</A11yHidden>
        <div css={profile} />
        <MdKeyboardArrowDown />
      </button>

      {isOpened && (
        <article css={dropDown}>
          <ul>
            <li>
              <FaUserAlt />
              <span>마이 페이지</span>
            </li>
            <li>
              <BsFillGearFill />
              <span>계정 관리</span>
            </li>
            <li>
              <FaSignOutAlt />
              <span>로그아웃</span>
            </li>
          </ul>
        </article>
      )}
    </div>
  );
};

const container = css`
  position: relative;
`;

const button = css`
  display: flex;
  align-items: center;
  outline: none;
`;

const dropDown = css`
  position: absolute;
  right: 0;
  top: ${pxToRem(30)};
  min-width: ${pxToRem(130)};
  background-color: white;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);

  li {
    padding: ${pxToRem(10)};

    span {
      padding-left: ${pxToRem(10)};
    }
  }
`;

const profile = css`
  background-color: #e0e0e0;
  width: ${pxToRem(25)};
  height: ${pxToRem(25)};
  border-radius: 50%;
`;

export default UserButton;
