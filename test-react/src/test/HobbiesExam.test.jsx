import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { describe, expect, it } from "vitest";
import HobbiesExam from "../exam/HobbiesExam";


const user = userEvent.setup();

describe('취미 체크 테스트', () => {
    it('첫 렌더링 시', () => {
        render(<HobbiesExam />);

        const movieCheck = screen.getByLabelText('영화');
        const musicCheck = screen.getByLabelText('음악감상');
        const walkingCheck = screen.getByLabelText('산책');
        const gameCheck = screen.getByLabelText('게임하기');

        expect(movieCheck).not.toBeChecked();
        expect(musicCheck).not.toBeChecked();
        expect(walkingCheck).not.toBeChecked();
        expect(gameCheck).not.toBeChecked();
    })

    it('체크박스 체크 시', async () => {
        render(<HobbiesExam />);

        const movieCheck = screen.getByLabelText('영화');
        const musicCheck = screen.getByLabelText('음악감상');
        const walkingCheck = screen.getByLabelText('산책');
        const gameCheck = screen.getByLabelText('게임하기');

        await user.click(musicCheck);
        await user.click(gameCheck);

        expect(movieCheck).not.toBeChecked();
        expect(walkingCheck).not.toBeChecked();
        expect(musicCheck).toBeChecked();
        expect(gameCheck).toBeChecked();
    })

    it('체크박스 체크 안하고 확인 버튼 클릭 시', async () => {
        render(<HobbiesExam />);

        const movieCheck = screen.getByLabelText('영화');
        const musicCheck = screen.getByLabelText('음악감상');
        const walkingCheck = screen.getByLabelText('산책');
        const gameCheck = screen.getByLabelText('게임하기');

        expect(movieCheck).not.toBeChecked();
        expect(walkingCheck).not.toBeChecked();
        expect(musicCheck).not.toBeChecked();
        expect(gameCheck).not.toBeChecked();

        const button = screen.getByRole('button', { name: '확인' });
        await user.click(button);
        expect(screen.getByText('선택 없음'));
    })

    it('체크박스 체크 후 확인 버튼 클릭 시', async () => {
        render(<HobbiesExam />);

        const movieCheck = screen.getByLabelText('영화');
        const musicCheck = screen.getByLabelText('음악감상');
        const walkingCheck = screen.getByLabelText('산책');
        const gameCheck = screen.getByLabelText('게임하기');

        await user.click(musicCheck);
        await user.click(gameCheck);

        expect(movieCheck).not.toBeChecked();
        expect(walkingCheck).not.toBeChecked();
        expect(musicCheck).toBeChecked();
        expect(gameCheck).toBeChecked();

        const button = screen.getByRole('button', { name: '확인' });

        await user.click(button);

        expect(screen.getByText('음악감상, 게임하기'));
    })
})