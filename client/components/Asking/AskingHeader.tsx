import { User } from "types/asking";
import { css } from "@emotion/react";
import Button from "components/common/Button";

function AskingHeader({ id, content, name, profile_url }: User) {
  return (
    <div css={headerWrapper}>
      <div css={profile}>
        {profile_url ? (
          <img src={profile_url} alt={`${name}의 프로필`} />
        ) : (
          <div className="profile-default" />
        )}
      </div>
      <div css={userInfo}>
        <span className="name">@{name}</span>
        <span className="content">{content}</span>
        <div className="button-wrapper">
          <Button>질문하기</Button>
          <Button>공유하기</Button>
        </div>
      </div>
    </div>
  );
}

const headerWrapper = css`
  display: flex;
  justify-content: space-around;
  align-items: flex-start;
  margin: 47px 34px;
`;

const userInfo = css`
  display: flex;
  flex-direction: column;
  margin-left: 20px;
  max-width: 300px;
  min-width: 200px;
  flex-wrap: nowrap;

  .name {
    font-weight: 700;
    margin-bottom: 17px;
  }
  .button-wrapper {
    margin-top: 17px;
    button:first-child {
      margin-right: 11px;
    }
  }
`;

const profile = css`
  position: relative;
  flex-shrink: 0;
  max-width: 100px;
  width: 100%;
  aspect-ratio: 1 / 1;
  border-radius: 100%;
  overflow: hidden;
  margin: 0 auto;

  img {
    flex-shrink: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .profile-default {
    width: 100%;
    height: 100%;
    background-color: #ededed;
  }
`;

export default AskingHeader;
