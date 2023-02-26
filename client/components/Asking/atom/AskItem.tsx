import { css } from "@emotion/react";
import { PostType } from "types/asking";

type AskItemProps = { isAnswer?: boolean } & PostType;

function AskItem({
  name,
  profile_url,
  date,
  contents,
  isAnswer,
}: AskItemProps) {
  return (
    <div css={askItem} className={isAnswer ? "isAnswer" : ""}>
      <div css={userInfo}>
        <div className="profile">
          {profile_url ? (
            <img
              src={profile_url}
              alt={`${name}의 프로필`}
              aria-hidden="true"
            />
          ) : (
            <div className="profile-default" />
          )}
        </div>
        <div className="user-detail">
          <span className="name">{name || "익명"}</span>
          <span className="date">{date.toDateString()}</span>
        </div>
      </div>

      <div css={contentWrapper}>{contents}</div>
    </div>
  );
}

const contentWrapper = css`
  margin: 17px 0;
`;

const userInfo = css`
  display: flex;
  align-items: center;

  .user-detail {
    display: flex;
    width: 100%;
    margin-left: 14px;
    flex-direction: column;
    .name {
      font-weight: 700;
      margin-bottom: 5px;
    }
    .date {
      font-size: 12px;
    }
  }

  .profile {
    position: relative;
    flex-shrink: 0;
    max-width: 41px;
    width: 100%;
    aspect-ratio: 1 / 1;
    border-radius: 100%;
    overflow: hidden;

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
  }
`;

const askItem = css`
  display: flex;
  flex-direction: column;
  margin: 17px;

  &.isAnswer {
    margin-left: 49px;
  }
`;

export default AskItem;
